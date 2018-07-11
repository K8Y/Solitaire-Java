import java.util.*;  // Vector class is in the 'util'  package
import java.awt.*;

public class DeckClass extends CardClass
{
    protected Vector deck = new Vector (0, 1);


    public DeckClass ()
    {
    }


    public DeckClass (char deckType)
    {
	iCentreX = 100;
	iCentreY = 100;

	if (deckType == 's') // std deck
	{
	    for (int i = 0 ; i < 13 ; i++)
	    {
		for (int j = 1 ; j <= 4 ; j++)
		{
		    CardClass card = new CardClass (j, i);
		    addCard (card, 0);
		}
	    }
	}
	if (deckType == 'h') //shuffled std deck
	{
	    for (int i = 0 ; i < 13 ; i++)
	    {
		for (int j = 1 ; j <= 4 ; j++)
		{
		    CardClass card = new CardClass (j, i);
		    addCard (card, 0);
		}
	    }
	    this.shuffle ();
	}

    }


    public DeckClass (int numDecks)  //x number of std decks
    {
	iCentreX = 100;
	iCentreY = 100;
	for (int k = 0 ; k < numDecks ; k++)
	{
	    for (int i = 0 ; i < 13 ; i++)
	    {
		for (int j = 1 ; j <= 4 ; j++)
		{
		    CardClass card = new CardClass (j, i);
		    addCard (card, 0);
		}
	    }
	}
    }


    public DeckClass (char deckType, boolean face, int size)
    {
	iCentreX = 100;
	iCentreY = 100;

	if (deckType == 's') // std deck
	{
	    for (int i = 0 ; i < 13 ; i++)
	    {
		for (int j = 1 ; j <= 4 ; j++)
		{
		    CardClass card = new CardClass (j, i, face);
		    setHeight (size);
		    card.setHeight (size);
		    addCard (card, 0);
		}
	    }
	}
    }


    public void flipTop ()
    {
	if (!deck.isEmpty ())
	{
	    flip ((CardClass) deck.firstElement ());
	}
    }


    public boolean isDeckEmpty ()
    {
	return deck.isEmpty ();
    }


    public CardClass topCard ()
    {
	return (CardClass) deck.get (0);
    }


    // public void addCard (int Pos, CardClass cardToAdd)  //if Pos == 0, it will add to the front rather than the back
    // {
    //     cardToAdd.toImgCentre ();
    //     if (deck.size () == 0)
    //     {
    //         deck.addElement (cardToAdd);
    //     }
    //     else if (Pos > deck.size ())
    //     {
    //         deck.insertElementAt (cardToAdd, deck.size ());
    //     }
    //     else
    //     {
    //         deck.insertElementAt (cardToAdd, Pos);
    //     }
    // }



    public void addCard (CardClass cardToAdd, int Pos)
    {
	// cardToAdd.toImgCentre ();

	// System.out.println (this.iCentreY);
	// cardToAdd.setCentre (iCentreX, iCentreY);
	// System.out.println (cardToAdd.getCenterY ());

	if (Pos == 0 || deck.size () == 0)
	{
	    cardToAdd.setCentre (getCenterX (), getCenterY ());
	    deck.addElement (cardToAdd);
	}
	else if (Pos > deck.size ())
	{
	    cardToAdd.setCentre (getCenterX (), getCenterY ());
	    deck.insertElementAt (cardToAdd, deck.size ());
	}
	else
	{
	    cardToAdd.setCentre (getCenterX (), getCenterY ());
	    deck.insertElementAt (cardToAdd, Pos);
	}
    }


    public void addDeck (DeckClass deckToAdd)  //add deck to end
    {
	deck.addElement (deckToAdd);
    }


    public void addCard (CardClass cardToAdd)
    {
	cardToAdd.setCentre (iCentreX, iCentreY);
	deck.addElement (cardToAdd);
    }


    public CardClass dealCard (int Pos)
    {
	return (CardClass) deck.remove (Pos);   // must type cast element
    }


    public void shuffle ()
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    deck.insertElementAt ((deck.remove ((int) (Math.random () * ((deck.size () - 1) + 1)))), ((int) (Math.random () * ((deck.size () - 1) + 1))));
	}
    }


    public void draw (Graphics g)
    {
	if (!deck.isEmpty ())
	{
	    CardClass temp = (CardClass) deck.lastElement ();
	    temp.draw (g);
	}
	else
	{
	    //setHeight (iHeight);
	    g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);

	}
    }


    public void draw (Graphics g, int pos, int x, int y)
    {
	iCentreX = x;
	iCentreY = y;
	if (!deck.isEmpty ())
	{
	    erase (g);
	    if (pos < deck.size ())
	    {
		CardClass card = (CardClass) deck.get (pos);
		card.setCentre (x, y);
		card.draw (g);
	    }
	    else
	    {
		CardClass card = (CardClass) deck.get (0);
		setCentre (x, y);
		card.setCentre (x, y);
		card.draw (g);
	    }
	}
	else
	{
	    setHeight (iHeight);
	    g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	}
    }


    public void draw (Graphics g, int x, int y)
    {
	setCentre(x, y);
	if (!deck.isEmpty ())
	{
	    // CardClass card = (CardClass) deck.get (0);
	    // card.setCentre (x, y);
	    // setCentre (x, y);
	    // card.draw (g);
	    ((CardClass) (deck.lastElement ())).draw (g);
	}
	else
	{
	    setHeight (iHeight);
	    setCentre (x, y);
	    g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth + 2, iHeight - 5);
	}
    }


    public void draw (Graphics g, int pos)
    {
	if (!deck.isEmpty ())
	{
	    if (pos < deck.size ())
	    {
		CardClass card = (CardClass) deck.get (pos);
		card.draw (g);
	    }
	    else
	    {
		CardClass card = (CardClass) deck.get (0);
		card.draw (g);
	    }
	}
	else
	{
	    setHeight (iHeight);
	    g.drawRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight);
	}
    }


    public void fanOut (String direction, Graphics g)
    {
	if (direction == "left")
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) (deck.elementAt (i))).setFaceUp (true);
		draw (g, i, iCentreX - (iWidth / 4), iCentreY);
	    }
	}
	if (direction == "right")
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) (deck.elementAt (i))).setFaceUp (true);
		draw (g, i, iCentreX + (iWidth / 4), iCentreY);
	    }
	}
	if (direction == "up")
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) (deck.elementAt (i))).setFaceUp (true);
		draw (g, i, iCentreX, iCentreY - (iWidth / 4));
	    }
	}
	if (direction == "up")
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) (deck.elementAt (i))).setFaceUp (true);
		draw (g, i, iCentreX, iCentreY + (iWidth / 4));
	    }
	}
    }


    // public void fixImgCentre (DeckClass d1)
    // {
    //     for (int i = 0 ; i > d1.deck.size () ; i++)
    //     {
    //         d1.deck.get (i).toImgPos ();
    //     }
    // }

    public void setCentre (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
	for (int i = 0 ; i < deck.size () ; ++i)
	{
	    ((CardClass) (deck.elementAt (i))).setCentre (iCentreX, iCentreY);
	}
    }

    public boolean isPointInside (int x, int y)
    {
	return (x <= iCentreX + (iWidth / 2) && x >= iCentreX - (iWidth / 2) && y <= iCentreY + (iHeight / 2) && y >= iCentreY - (iHeight / 2));
	// return (x <= iCentreX + iWidth/2 && x >= iCentreX -iWidth/2  && y <= iCentreY + iHeight/2 && y >= iCentreY-iHeight/2);
    }
} // DeckClass class

