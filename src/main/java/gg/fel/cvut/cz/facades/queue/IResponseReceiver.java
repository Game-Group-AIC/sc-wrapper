package gg.fel.cvut.cz.facades.queue;

/**
 * Interface for response receiver defining method to send response Created by Jan on 17-Feb-17.
 */
public interface IResponseReceiver<V> {

  /**
   * Method is called on receiver to end him response
   */
  void receiveResponse(V response);

}
