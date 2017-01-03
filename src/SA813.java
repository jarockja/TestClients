/*
 * This class implements the main program.
 * It will connect to VSE, get a map from a cluster and reads
 * all data from this cluster into a Vector.
 * Then it outputs the records to the console.
 */

import java.lang.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.resource.*;
import com.ibm.vse.connector.*;

public class SA813
{
   public static void main(String argv[]) throws IOException, ResourceException
   {
      VSEConnectionSpec spec = null;
      VSESystem system = null;
      VSEVsam vsam = null;
      VSEVsamCatalog catalog = null;
      VSEVsamCluster cluster = null;
      VSEVsamMap map = null;
      myRecListener rl = null;
      Vector vRecords = null;

      String ipAddr = "10.49.103.238";
      String userID = "connect";
      String password = "connect";
      String catName  = "ONLCAT";
      String fileName = "P.ABR.LIEGEN";
      String mapName  = "LIEGEN.MAP.SA813";

      System.out.println("Creating connection and VSE system ...");
      try {
          spec = new VSEConnectionSpec(InetAddress.getByName(ipAddr), 2893,userID,password);
      }
      catch (UnknownHostException e)
      {   System.out.println("Unknown host : " + e); return;
      }

      /* Stay logon with this user for lifetime of this connection */
      spec.setLogonMode(true);

      /* Create VSE system instance with this connection */
      system = new VSESystem(spec);

      /* Get file system from host */
      System.out.println("Getting records from " + fileName);
      vsam = system.getVSEVsam();

      /* Step 2: Get VSAM records from host using the given map */
      map = new VSEVsamMap(system, catName, fileName, mapName);
      cluster = new VSEVsamCluster(system, catName, fileName);

      rl = new myRecListener(map);
      cluster.addVSEResourceListener(rl);
      cluster.selectRecords(map);
      cluster.removeVSEResourceListener(rl);

      /* Get vector containing all records from listener */
      vRecords = rl.getRecords();

      /* Step 3: Display records ... */
      for (int k=0; k<vRecords.size(); k++)
      {
         System.out.println("Record " + k + ":");
         myVsamData myData = (myVsamData)(vRecords.elementAt(k));

         System.out.println("SA813-001N = " + myData.SA813_001N.toString() );
         System.out.println("SA813-002N = " + myData.SA813_002N.toString() );
         System.out.println("SA813-010-FIELD = " + myData.SA813_010_FIELD.toString() );
         System.out.println("SA813-100N = " + myData.SA813_100N.toString() );
         System.out.println("SA813-110N = " + myData.SA813_110N.toString() );
         System.out.println("SA813-120N = " + myData.SA813_120N.toString() );
         System.out.println("FILLER-1 = " + myData.FILLER_1.toString() );
         System.out.println("SA813-020N = " + myData.SA813_020N.toString() );
         System.out.println("FILLER-2 = " + myData.FILLER_2.toString() );
         System.out.println("------------------------------");
      }
   }
}
