import java.util.*;

import com.ibm.vse.connector.*;

/**
 * Class VsamListener
 * is called by the connector API when
 * the actual request finished. For each object retrieved from the host
 * the listAdded() method is called.
 *
 * @version 1.0
 *
 */
public class VsamListener implements VSEResourceListener
{
  public Vector catVector, fileVector;

  /**
   * constructs a new LibrListener.
   *
   */
  public VsamListener()
  {
    catVector = new Vector();
    fileVector = new Vector();
  }

  /**
   * returns the libVector
   *
   */
  public void clear()
  {
    catVector.removeAllElements();
    fileVector.removeAllElements();
  }

  /**
   * returns the catVector
   *
   */
  public Vector getCatalogVector()
  {
    return catVector;
  }

  /**
   * returns the fileVector
   *
   */
  public Vector getFileVector()
  {
    return fileVector;
  }

  /**
   * is called for the start of a List of VSEResources.
   * Before notifying about the list elements, this method is called.
   * The event does not contain any data.
   *
   * @param event The VSEResourceEvent containing the source
   */
  public void listStarted(VSEResourceEvent event)
  {
    System.out.println("VsamListener: listStarted()");
  }

  /**
   * is called for each element of a List of VSEResources.
   * It is called for each element of the list. The VSEResourceEvent
   * conatins the element (see getData()).
   *
   * @param event The VSEResourceEvent containing the source and the data
   */
  public void listAdded(VSEResourceEvent event)
  {
    VSEResource resource = event.getData();
    System.out.println("Adding callback data: " + resource);

    if (resource instanceof VSEVsamCatalog)
    {
      VSEVsamCatalog cat = (VSEVsamCatalog)resource;
      catVector.addElement(cat);
      System.out.println("VsamListener: listAdded(), cat = " + cat.getFileID() + ", name=" + cat.getName());
    }
    else if (resource instanceof VSEVsamCluster)
    {
      VSEVsamCluster file = (VSEVsamCluster)resource;
      fileVector.addElement(file);
      System.out.println("VsamListener: listAdded(), cluster = " + file.getFileID() + ", name=" + file.getName());
    }
    else
      System.out.println("VsamListener: listAdded(), other VSE resource = " + resource.toString());
  }

  /**
   * is called for the end of a List of VSEResources.
   * After notifying about the list elements, this method is called.
   * The event does not contain any data
   *
   * @param event The VSEResourceEvent containing the source
   */
  public void listEnded(VSEResourceEvent event)
  {
    System.out.println("VsamListener: listEnded()");
  }
}
