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

/**
 * Class JdbcExample.
 * @version 1.0
 *
 */
public class ABREVsamLiegenClient
{
  // Default port number.
  static String   vsePort     = "2893";

  // Names of the Clusters and Maps.
  static String vsamCatalog    = "ONLCAT";
  static String clusterName = "P.ABR.LIEGEN";
  static String mapName = "LIEGEN.MAP.SA813";

  /**
   */
  public static void main(String argv[]) throws Exception {

    VSESystem system = null;
    try {
      String vseHost = "10.49.103.238", userID = "connect", password = "connect";

      VSEConnectionSpec spec = new VSEConnectionSpec(InetAddress.getByName(vseHost), 2893, userID, password);
      system = new VSESystem(spec);

      VSEVsamMap map = new VSEVsamMap(system, vsamCatalog, clusterName, mapName);

      if (map.isExistent()) {
        map.delete();
        System.out.println("Map already exist...");
      }
      else {
        map.create();
        map.addField(new VSEVsamField(system, "SUCHBEGRIFF_1", VSEVsamMap.TYPE_PACKED, 13, 0));
        map.addField(new VSEVsamField(system, "LFD-NR", VSEVsamMap.TYPE_PACKED, 5, 13));
        map.addField(new VSEVsamField(system, "NOTIZ-EINZELZEILE", VSEVsamMap.TYPE_STRING, 79, 18));
        map.addField(new VSEVsamField(system, "ERF-DATUM-KUNDENTEXT", VSEVsamMap.TYPE_UNSIGNED, 8, 97));
        map.addField(new VSEVsamField(system, "FREI", VSEVsamMap.TYPE_STRING, 3, 105));
        System.out.println("Map does not exist. Creating...");
        System.out.println("Key-Fields: " + map.getPrimaryKeyFields());
      }

      ABREVsamListener listener = new ABREVsamListener();
      VSEVsamCluster cluster = new VSEVsamCluster(system, vsamCatalog, clusterName);
      cluster.addVSEResourceListener(listener);
      cluster.setMaxRecords(100);
      cluster.selectRecords(map);
      cluster.removeVSEResourceListener(listener);

    } finally {
      system.disconnect();
    }
  }
}
