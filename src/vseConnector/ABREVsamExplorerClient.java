package vseConnector; /******************************************************************************/
/*           VSE/ESA Connector Framework - Example Code                       */
/******************************************************************************/
/*                                                                            */
/* MODULE NAME : JdbcExample.java                                             */
/*                                                                            */
/* DESCRIPTIVE NAME : Shows how to issue SQL requests against VSAM data.      */
/*                                                                            */
/* RELEASE : VSE/ESA Version 2.6.0                                            */
/*                                                                            */
/* COPYRIGHT NOTICE: Copyright (C) 2000, 2001.  IBM Corporation.              */
/*                                                                            */
/*   This file is used to demonstrate how to utilize IBM Corporation's        */
/*   VSE/ESA Connector Framework Java classes.                                */
/*                                                                            */
/*   You have a royalty-free right to use, modify, reproduce and              */
/*   distribute this demonstration file (including any modified               */
/*   version), provided that you agree that IBM Corporation                   */
/*   has no warranty, implied or otherwise, or liability                      */
/*   for this demonstration file or any modified version.                     */
/*                                                                            */
/* COMPILER :                                                                 */
/*                                                                            */
/*      Java 1.3   or higher                                                  */
/*                                                                            */
/******************************************************************************/
/*                                                                            */
/* CHANGE ACTIVITY :                                                          */
/*                                                                            */
/*                                                                            */
/******************************************************************************/
/*

Description of the VSAM Clusters used for this Sample:
------------------------------------------------------

The FlightOrdering Servlet uses two VSAM Clusters:

1.) FLIGHT.ORDERING.FLIGHTS (FLIGHTS)  - KSDS

Both are defined (as default) in the VSESP.USER.CATALOG (VSESPUC).

The FLIGHT.ORDERING.FLIGHTS Cluster:
-----------------------------------

The Record Layout of the FLIGHTS Cluster is defined as follows:

Offset Length Type      Key    Field Name     Description
-------------------------------------------------------------------
0      4      UNSIGNED  yes    FLIGHT_NUMBER  Flight Number
4      20     STRING    no     START          Start
24     20     STRING    no     DESTINATION    Destination
44     5      STRING    no     DEPARTURE      Departure (hh:mm)
49     5      STRING    no     ARRIVAL         arrival    (hh:mm)
54     4      UNSIGNED  no     SEATS          Seats
58     4      UNSIGNED  no     RESERVED       Seats reserved
62     4      PACKED    no     PRICE          Price
66     20     STRING    no     AIRLINE        Airline

The Key of the Cluster is a 4 Byte field at Offset 0.
The Record Length is 86 bytes.
The Record Layout is reflected in the Map FLIGHTS_MAP

The following Job can be used to define the cluster:

* $$ JOB JNM=DEFINE,CLASS=0,DISP=D
// JOB DEFINE CLUSTERS FOR FLIGHT ORDERING SERVLET
// EXEC IDCAMS,SIZE=AUTO
 DEFINE CLUSTER ( -
        NAME (FLIGHT.ORDERING.FLIGHTS                      ) -
        CYLINDERS(2        2          ) -
        SHAREOPTIONS (2) -
        RECORDSIZE (86     86    ) -
        VOLUMES (DOSRES SYSWK1 ) -
        NOREUSE -
        INDEXED -
        FREESPACE (15 7) -
        KEYS (4   0     ) -
        NOCOMPRESSED -
        TO (99366  )) -
        DATA (NAME (FLIGHT.ORDERING.FLIGHTS.DATA                  ) -
        CONTROLINTERVALSIZE (4096  )) -
        INDEX (NAME (FLIGHT.ORDERING.FLIGHTS.INDEX               )) -
        CATALOG (VSESP.USER.CATALOG                         )
  IF LASTCC NE 0 THEN CANCEL JOB
/*
// OPTION STDLABEL=ADD
// DLBL FLIGHTS,'FLIGHT.ORDERING.FLIGHTS',,VSAM,CAT=VSESPUC
/*
// EXEC IESVCLUP,SIZE=AUTO
A FLIGHT.ORDERING.FLIGHTS                      FLIGHTS VSESPUC
/*
/&
* $$ EOJ

*/

import com.ibm.vse.connector.*;

import java.net.InetAddress;
import java.util.Vector;

/**
 * Class JdbcExample.
 * @version 1.0
 *
 */
public class ABREVsamExplorerClient
{
  // Default port number.
  static String   vsePort     = "2893";

  // Names of the Clusters and Maps.
  static String vsamCatalog    = "ABRE.UCAT";
  static String clusterName = "P.VEX.DELTA";
  static String mapName = "MAPDELTA";

  /**
   */
  public static void main(String argv[]) throws Exception {

    VSESystem system = null;

    try {
      String vseHost = "10.49.103.238", userID = "connect", password = "connect";

      VSEConnectionSpec spec = new VSEConnectionSpec(InetAddress.getByName(vseHost), 2893, userID, password);
      spec.setLogonMode(true);

      system = new VSESystem(spec);
      VSEVsam vsam = system.getVSEVsam();
//      ABREVsamListener recordListener = new ABREVsamListener();
//      vsam.addVSEResourceListener(recordListener);
//      vsam.getCatalogList();
//      vsam.removeVSEResourceListener(recordListener);

      VSEVsamCatalog cat = vsam.getVSEVsamCatalog("ONLINE.UCAT");
      VSEVsamCluster liegenCluster = cat.getVSEVsamCluster("P.ABR.LIEGEN");

//      vsam.addVSEResourceListener(recordListener);
//      liegenCluster.getMapList();
//      vsam.removeVSEResourceListener(recordListener);

      final VSEVsamMap map = liegenCluster.getVSEVsamMap("LIB801D.CPY");

      VSEVsamFilter filter = liegenCluster.getVSEVsamFilter();
      filter.setField(map.getField(1));
      filter.setFilter(new Long("1544400980801"));
      filter.setOperation(VSEVsamFilter.OPERATION_EQUALS);
      final VSEVsamRecord record1 = liegenCluster.getFirstRecord(map);
      //final VSEVsamRecord record2 = liegenCluster.getLastRecord(map);

      printRecord(record1);
      //printRecord(record2);

//      for (int i = 0; i < recordListener.getCatalogVector().size(); i++) {
//        VSEVsamCatalog catalog = (VSEVsamCatalog) (recordListener.getCatalogVector().elementAt(i));
//        if (catalog.getName().equals("ONLCAT")) {
//            /* Get cluster list */
//          catalog.addVSEResourceListener(recordListener);
//          catalog.getClusterList();
//          catalog.removeVSEResourceListener(recordListener);
//          Vector<VSEVsamCluster> vClusters = recordListener.getFileVector();
//          System.out.println("There are " + new Integer(vClusters.size()).toString() + " clusters in " + catalog.getName());
//          for (VSEVsamCluster cluster : vClusters) {
//            if (cluster.getName().equals("LIEGEN")) {
//              System.out.println("reading connector maps for cluster: " + cluster.getName());
//              cluster.addVSEResourceListener(recordListener);
//              cluster.getMapList();
//              cluster.removeVSEResourceListener(recordListener);
//            }
//          }
//        }
//      }
    }
    finally {
      system.disconnect();
    }
  }
  private static void printRecord(VSEVsamRecord record) throws Exception {

    for(int i = 0; i < record.getNumberOfFields(); i++){
      System.out.println("field: " + record.getFieldName(i) + " - " + record.getField(i));
    }

  }
}
