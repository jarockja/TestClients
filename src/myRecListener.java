import java.lang.*;
import java.util.*;
import com.ibm.vse.connector.*;
/*
 * This class implements the RecordListener.
 * The listener will create a Vector of instances of the data class
 * filled with the record data.
 */

public class myRecListener implements VSEResourceListener
{
   Vector vRecs = null;
   VSEVsamMap theMap = null;

   public myRecListener(VSEVsamMap map)
   {
      this.vRecs = new Vector();
      this.theMap = map;
   }

   public void listStarted(VSEResourceEvent event) {}

   public void listEnded(VSEResourceEvent event)  {}

   public Vector getRecords()
   {
      return this.vRecs;
   }

   public void listAdded(VSEResourceEvent event)
   {
      VSEResource resource = (VSEResource)(event.getData());

      if (resource instanceof VSEVsamRecord)
      {
         VSEVsamRecord rec = (VSEVsamRecord)resource;
         myVsamData myData = new myVsamData();

         try {
         myData.SA813_001N = (Number )rec.getField( theMap.getIndex( "SA813-001N" ) );
         myData.SA813_002N = (Number )rec.getField( theMap.getIndex( "SA813-002N" ) );
         myData.SA813_010_FIELD = (String )rec.getField( theMap.getIndex( "SA813-010-FIELD" ) );
         myData.SA813_100N = (Number )rec.getField( theMap.getIndex( "SA813-100N" ) );
         myData.SA813_110N = (Number )rec.getField( theMap.getIndex( "SA813-110N" ) );
         myData.SA813_120N = (Number )rec.getField( theMap.getIndex( "SA813-120N" ) );
         myData.FILLER_1 = (String )rec.getField( theMap.getIndex( "FILLER-1" ) );
         myData.SA813_020N = (Number )rec.getField( theMap.getIndex( "SA813-020N" ) );
         myData.FILLER_2 = (String )rec.getField( theMap.getIndex( "FILLER-2" ) );
         this.vRecs.addElement(myData);
         } catch(Exception ex) { System.out.println("IO Error:"+ex); }
      }
   }

}
