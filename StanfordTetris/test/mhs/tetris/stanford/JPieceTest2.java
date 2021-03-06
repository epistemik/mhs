/* ***************************************************************************************
 * 
 *  Mark Sattolo (epistemik@gmail.com)
 * -----------------------------------------------
 * $File: //depot/Eclipse/Java/workspace/StanfordTetris/test/mhs/tetris/stanford/test/JPieceTest.java $
 * $Revision: #1 $ 
 * $Change: 160 $ 
 * $DateTime: 2012/01/02 06:33:39 $
 * -----------------------------------------------
 * 
 * mhs.tetris.stanford.JPieceTest.java 
 * Eclipse version created on Jan 2, 2012
 * 
 * ***************************************************************************************
 */

package mhs.tetris.stanford;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Debugging client for the Piece class. The JPieceTest component draws all the rotations
 * of a tetris piece. JPieceTest.main() creates a frame with one JPieceTest for each of
 * the 7 standard tetris pieces.
 * 
 * This is the starter file version -- The outer shell is done. You need to complete
 * paintComponent() and drawPiece().
 */
class JPieceTest2 extends JComponent
{
  /** keep the compiler from complaining */
  private static final long serialVersionUID = -1938011970392936777L;

  protected Piece root;

  public JPieceTest2( Piece piece, int width, int height )
  {
    super();

    setPreferredSize( new Dimension( width, height ) );

    root = piece;
  }

  /**
   * Draws the rotations from left to right. Each piece goes in its own little box.
   */
  public final int MAX_ROTATIONS = 4;

  public void paintComponent( Graphics g )
  {
    Piece cur = root;
    Rectangle rect = new Rectangle();
    rect.y = 0;
    if( getWidth() / 4 > getHeight() )
    {
      rect.width = getHeight();
      rect.height = rect.width;
    }
    else
    {
      rect.width = getWidth() / 4;
      rect.height = rect.width;
    }
    for( int i = 0; i < 4; i++ )
    {
      rect.x = i * ( getWidth() / 4 );
      drawPiece( g, cur, rect );
      cur = cur.nextRotation();
      if( cur == root )
        break;
    }

  }

  /**
   * Draw the piece inside the given rectangle.
   */
  private void drawPiece( Graphics g, Piece piece, Rectangle r )
  {
    // note that the internal piece representation is opposite the screen coords in y
    Point[] draw = piece.getBody();

    for( int i = 0; i < draw.length; i++ )
    {
      if( piece.getSkirt()[draw[i].x] == draw[i].y )
      {
        g.setColor( Color.yellow );
      }
      else
      {
        g.setColor( Color.black );
      }

      g.fillRect( r.x + draw[i].x * ( r.width / 4 ) + 1, r.y + r.height
          - ( draw[i].y + 1 ) * ( r.height / 4 ) + 1, ( r.width / 4 ) - 1,
          ( r.height / 4 ) - 1 );
    }
    g.setColor( Color.red );
    g.drawString( "w:" + piece.getWidth() + " h:" + piece.getHeight(), r.x, r.y
        + r.height );
  }

  /**
   * Draws all the pieces by creating a JPieceTest for each piece, and putting them all in
   * a frame.
   * @param args - from command line
   */
  static public void main( String[] args )

  {
    JFrame frame = new JFrame( "Piece Tester" );
    JComponent container = (JComponent)frame.getContentPane();

    // Put in a BoxLayout to make a vertical list
    container.setLayout( new BoxLayout( container, BoxLayout.Y_AXIS ) );

    Piece[] pieces = Piece.getPieces();

    for( int i = 0; i < pieces.length; i++ )
    {
      JPieceTest2 test = new JPieceTest2( pieces[i], 375, 75 );
      container.add( test );
    }

    // Size the window and show it on screen
    frame.pack();
    frame.setVisible( true );

    // Quit on window close
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing( WindowEvent e )
      {
        System.exit( 0 );
      }
    } );
  }
}
