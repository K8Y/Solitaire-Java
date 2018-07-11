// The "GameApplet" class.
//Courtyard Solitaire
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameApplet extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    Graphics g;
    Panel foundations = new Panel (); //spaces on the top. aim of game is to fill these.
    Panel tableau = new Panel (); //centre. cards can be placed here
    Panel stock = new Panel (); //waste and draw piles

    BorderLayout lm = new BorderLayout ();

    boolean OKtoMove = false;

    DeckClass foundationD[] = new DeckClass [8];  //array of foundation deck
    DeckClass tableauD[] = new DeckClass [12]; //array of tableau decks
    DeckClass wasteD = new DeckClass ();
    DeckClass drawD = new DeckClass ('2');

    boolean winFlag = false;
    boolean loseFlag = false;
    boolean validMove = false;

    DeckClass takenFromDeck = new DeckClass (); //dummy deck class
    CardClass test = new CardClass ();

    DeckClass draggedCards = new DeckClass ();

    CardClass dummy = new CardClass();

    public void initializations ()
    {
	for (int i = 0 ; i < 8 ; i++)
	{
	    foundationD [i] = new DeckClass ();
	}

	for (int i = 0 ; i < 12 ; i++)
	{
	    tableauD [i] = new DeckClass ();
	}

	dummy = new CardClass (1, 2);
	dummy.setCentre (foundationD[0].iCentreX, foundationD[0].iCentreY);
	foundationD [0].addCard (dummy);
	dummy = new CardClass(3,4);
	dummy.setCentre (foundationD[2].iCentreX, foundationD[2].iCentreY);
	foundationD [2].addCard (dummy);
    }


    public void init ()
    {
	setLayout (lm);
	//  foundations.setLayout (new FlowLayout (FlowLayout.CENTER, 20, 20));
	setSize (1200, 600);
	initializations ();




	addMouseListener (this);
	addMouseMotionListener (this);


	// Place the body of the initialization method here
    } // init method


    public boolean validMove (DeckClass deck, CardClass card)
    {
	if (deck.isDeckEmpty ())
	{
	    return true;
	}
	else if (card.cSuit == deck.topCard ().cSuit && card.faceValue == deck.topCard ().faceValue)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public void chooseCard (DeckClass deck)  //determines which card was clicked on
    {
	CardClass temp = new CardClass ();

	if (!deck.isDeckEmpty ())
	{
	    temp = deck.dealCard (0);
	    
	    draggedCards.addCard (temp);

	    takenFromDeck = deck;
	}
    }




    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();


    }


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    // public void moveCard (DeckClass d1)
    // {
    //     if (validMove (d1, (CardClass) draggedCards.deck.get (0)))
    //     {
    //         d1.deck.add (draggedCards);
    //
    //     }
    //     else
    //     {
    //         takenFromDeck.deck.add (draggedCards);
    //
    //     }
    // }


    public void mousePressed (MouseEvent e)
    {
    

	for (int i = 0 ; i < 8 ; i++)
	{
	    if (foundationD [i].isPointInside (e.getX (), e.getY ()) == true)
	    {
		chooseCard (foundationD [i]);
		OKtoMove = true;
	    }
	}

	for (int i = 0 ; i < 12 ; i++)
	{
	    if (tableauD [i].isPointInside (e.getX (), e.getY ()) == true)
	    {
		chooseCard (tableauD [i]);
		OKtoMove = true;
	    }
	}

	// OKtoMove = true;
	// test.setCentre (e.getX (), e.getY ());
	repaint ();
    }


    public void mouseReleased (MouseEvent e)
    {
	OKtoMove = false;

	if (draggedCards.isDeckEmpty ())
	{
	    return;
	}


	CardClass temp = (CardClass) draggedCards.deck.get (0);
	boolean moved = false;

	for (int i = 0 ; i < 8 ; i++)
	{
	    if (foundationD [i].isPointInside (e.getX (), e.getY ()))
	    {
		if (validMove (foundationD [i], temp))
		{
		    foundationD [i].deck.add (draggedCards);
		    moved = true;
		}
	    }
	}
	for (int i = 0 ; i < 12 ; i++)
	{
	    if (tableauD [i].isPointInside (e.getX (), e.getY ()))
	    {
		if (validMove (tableauD [i], temp))
		{
		    tableauD [i].deck.add (draggedCards);
		    moved = true;
		}
	    }
	}

	if (!moved)
	{
	    takenFromDeck.addCard (draggedCards);
	}
	repaint ();

    }


    public void mouseDragged (MouseEvent e)
    {
	if (OKtoMove == true)
	{
	    if (draggedCards.isDeckEmpty ())
	    {
		return;
	    }

	    for (int i = 0 ; i < draggedCards.deck.size () ; i++)
	    {
		CardClass temp = (CardClass) draggedCards.deck.get (i);
		temp.setCentre (e.getX (), e.getY ()); //not acctually centre, it's where mouse is
	    }
	    //test.setCentre (e.getX () - test.iWidth / 2, e.getY () - test.iHeight / 2);
	    repaint ();

	}

    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public void paint (Graphics g)
    {

	for (int i = 0 ; i < draggedCards.deck.size () ; i++)
	{
	    CardClass temp = (CardClass) draggedCards.deck.get (i);
	    temp.draw (g);
	}

	for (int i = 0 ; i < 8 ; i++)
	{

	    foundationD [i].setCentre (test.iWidth * i + 50, 50);
	    foundationD [i].draw (g);
	}

	for (int i = 0 ; i < 12 ; i++)
	{
	    tableauD [i].setCentre (test.iWidth * i + 50, 200);
	    tableauD [i].draw (g);
	}



	//g.drawImage (test.img, test.iCentreX, test.iCentreY, this);


	// Place the body of the drawing method here
    } // paint method
} // GameApplet class


