/******************************************************************************/
/*           VSE/ESA Connector Framework - Example Code                       */
/******************************************************************************/
/*                                                                            */
/* MODULE NAME : VsamDisplayExample.java                                      */
/*                                                                            */
/* DESCRIPTIVE NAME : Shows how to use the VSE/VSAM related classes.          */
/*                    Shows how to display and change VSAM records            */
/*                    using a data map.                                       */
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

import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

import javax.resource.*;

import com.ibm.vse.connector.*;

import static com.ibm.sslite.s.r;

/**
 * Class VsamDisplayExample
 * It is assumed that there is a cluster FLIGHT.ORDERING.FLIGHTS
 * in the user catalog, which contains a map FLIGHTS_MAP.
 * Refer to the FlightOrderingServlet example for POWER jobs
 * that create the sample clusters.
 *
 * @version 1.0
 */
public class ABREAccessClient
{
  /**
   */
  public static void main(String argv[]) throws IOException, ResourceException {
    VSEConnectionSpec spec;
    VSESystem system;
    VSEVsam vsam;
    VSEVsamCatalog catalog;
    VSEVsamCluster cluster;
    VSEVsamRecord record, newRec = null;
    VSEVsamMap map;
    VsamListener rl;
    String ipAddr = "10.49.103.238";
    String userID = "kristen", password = "kristen";
    List<VSEVsamRecord> vRecords;

    String catName = "ABRE.UCAT";
    String fileName = "P.VEX.DELTA";
    String mapName = "MAPDELTA";

      /* Create connection specification. */
    System.out.println("Creating connection and VSE system ...");
    try {
      spec = new VSEConnectionSpec(InetAddress.getByName(ipAddr), 2893, userID, password);
    } catch (UnknownHostException e) {
      System.out.println("Unknown host : " + e);
      return;
    }

      /* Stay logon with this user for lifetime of this connection */
    spec.setLogonMode(true);

      /* Create VSE system instance with this connection */
    system = new VSESystem(spec);

      /* Keep the connection for lifetime of this program */
    system.setConnectionMode(true);

      /* Get file system from host */
    System.out.println("Getting records from " + fileName + "...");
    vsam = system.getVSEVsam();

      /* Step 1: Create a listener that gets notified when objects */
      /* are retrieved from the host */
    rl = new VsamListener();
    vsam.addVSEResourceListener(rl);

      /* Step 2: Get VSAM records from host using the given map */
    map = new VSEVsamMap(system, catName, fileName, mapName);
    map.refresh();

    cluster = new VSEVsamCluster(system, catName, fileName);
    cluster.addVSEResourceListener(rl);
    cluster.selectRecords(map);
    cluster.removeVSEResourceListener(rl);

      /* Get vector containing all records from listener */
    vRecords = rl.getCatalogVector();

      /* Step 3: Display records ... */
    int numMapFields = map.getNoOfFields();
    System.out.println("Records in file " + fileName + " :");
    for (int k = 0; k < vRecords.size(); k++) {
      System.out.println("Record " + k + ":");
      record = vRecords.get(k);
      for (int i = 0; i < numMapFields; i++) {
        try {
          if (map.isFieldPartOfPrimaryKey(i))
            System.out.println(map.getFieldName(i) + " (Key) : " + record.getField(i).toString());
          else
            System.out.println(map.getFieldName(i) + " : " + record.getField(i).toString());
        } catch (Exception e) {
          System.out.println("writeRecord() : " + e);
        }
      }
      if (record.getRecordLength() > 0)
        System.out.println("Total record length in bytes: " + record.getRecordLength());
      System.out.println("------------------------------");
    }
  }
}
