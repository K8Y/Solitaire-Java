// The "GameApplet" class.
//Courtyard Solitaire
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GameApplet extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    //  instance variables 
    Graphics g;


    FlowLayout lm = new FlowLayout (FlowLayout.LEFT, 20, 20);
    int style = Font.BOLD | Font.ITALIC;
    Font font = new Font ("garamond", style, 100);

    boolean OKtoMove = false;
    boolean OKtoMoveWaste = false;

    Button buttonRestart = new Button ("Restart");
    Button buttonQuit = new Button ("Quit");

    DeckClass foundationD[] = new DeckClass [8];  //array of foundation deck
    DeckClass tableauD[] = new DeckClass [12]; //array of tableau decks
    DeckClass wasteD = new DeckClass ();
    DeckClass drawD = new DeckClass (2);

    TextField time = new TextField (10);
    long startTime;
    long endTime;



    boolean winFlag = false;
    boolean loseFlag = false;

    boolean wasteClicked = false;

    boolean validMove = false;


    CardClass dragCard = null;
    DeckClass dragPile = new DeckClass ();
    DeckClass takenFromDeck = new DeckClass ();


    public void initializations ()
    {
        foundationD = new DeckClass [8];  //array of foundation deck
        tableauD = new DeckClass [12]; //array of tableau decks
        wasteD = new DeckClass ();
        drawD = new DeckClass (2);

        dragCard = null;
        dragPile = new DeckClass ();
        takenFromDeck = new DeckClass ();

        winFlag = false;
        loseFlag = false;

        validMove = false;

        drawD.shuffle ();
        drawD.setCentre (800, 50);

        wasteD.setCentre (900, 50);

        for (int i = 0 ; i < 8 ; i++)
        {
            foundationD [i] = new DeckClass ();
            foundationD [i].setCentre (i * (foundationD [i].iWidth) + 50, 50);
        }

        for (int i = 0 ; i < 12 ; i++)
        {
            tableauD [i] = new DeckClass ();
            tableauD [i].addCard (drawD.dealCard (0));
            tableauD [i].setCentre (i * (tableauD [i].iWidth) + 50, 200);
        }

        //drawD.flipAllCards ();
        startTime = System.currentTimeMillis ();
        endTime = 0;
        
        System.out.println("You have 5 min to win the game. Cards can be stacked in the tableau, but only on top of cards of the same suit and in ascending order.");

    }


    public void init ()
    {
        setLayout (lm);
        setBackground (Color.pink);
        setSize (1200, 600);
        initializations ();

        addMouseListener (this);
        addMouseMotionListener (this);

        add (buttonRestart);
        add (buttonQuit);
        add (time);
        buttonRestart.addActionListener (this);  // this is the button
        buttonQuit.addActionListener (this);

        // body of the initialization method here
    } // init method


    public void update (Graphics g)  // gets rid of the flickering caused by repainting
    {
        Image offScreenImage = createImage (getWidth (), getHeight ());

        Graphics offScreenG = offScreenImage.getGraphics ();

        offScreenG.setColor (getBackground ());
        offScreenG.fillRect (getX (), getY (), getWidth (), getHeight ());
        offScreenG.setColor (getForeground ());

        paint (offScreenG);

        g.drawImage (offScreenImage, getX () - 6, getY () - 30, this);
    } // update method


    public void actionPerformed (ActionEvent e)
    {
        Object objSource = e.getSource ();

        if (objSource == buttonQuit)
        {
            System.exit (0);
        }
        else if (objSource == buttonRestart)
        {
            initializations ();
            repaint ();
        }
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


    public void chooseCard (DeckClass d1)
    {
        dragPile.addCard (d1.dealCard (d1.deck.size () - 1));
        takenFromDeck = d1;
    }


    public boolean validMove (DeckClass d1, char type)  // type of deck that card will be added to
    {



        if (type == 'f') //foundation
        {
            if (d1.isDeckEmpty ())
            {
                if (((CardClass) dragPile.deck.elementAt (0)).faceValue == 'A') // if empty and adding ace
                {
                    return true;
                }
            }
            else // not Empty deck
            {
                if (d1.lastCard ().faceValueNum + 1 == ((CardClass) dragPile.deck.elementAt (0)).faceValueNum && d1.lastCard ().cSuit == ((CardClass) dragPile.deck.elementAt (0)).cSuit)  // if adding same suit and card that is 1 higher
                {
                    return true;
                }
                else if (d1.lastCard ().faceValue == 'K' && ((CardClass) dragPile.deck.elementAt (0)).faceValue == 'A' && d1.lastCard ().cSuit == ((CardClass) dragPile.deck.elementAt (0)).cSuit) // add ace to king
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

        }


        if (type == 't') //tableau
        {
            if (d1.isDeckEmpty ())
            {
                return true;
            }
            else //deck not empty
            {
                if (d1.lastCard ().faceValueNum == ((CardClass) dragPile.deck.elementAt (0)).faceValueNum + 1 && d1.lastCard ().cSuit == ((CardClass) dragPile.deck.elementAt (0)).cSuit)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }


        return false;

    }


    public void mousePressed (MouseEvent e)
    {

        if (drawD.isPointInside (e.getX (), e.getY ()))
        {
            if (!drawD.isDeckEmpty ())
            {
                wasteD.addCard (drawD.dealCard (drawD.deck.size () - 1));
            }
            else
            {
                int temp = wasteD.deck.size ();
                for (int i = 0 ; i < temp ; i++)
                {
                    drawD.addCard (wasteD.dealCard (wasteD.deck.size () - 1));
                }
            }
        }


        for (int i = 0 ; i < 8 ; i++) //to check if foundations are clicked
        {
            if (foundationD [i].isPointInside (e.getX (), e.getY ()) && !foundationD [i].isDeckEmpty ())
            {
                chooseCard (foundationD [i]);
                OKtoMove = true;
            }
        }


        for (int i = 0 ; i < 12 ; i++)
        {
            int temp = tableauD [i].deck.size (); //tableaus

            for (int j = 0 ; j < temp ; j++)
            {
                if (j < tableauD[i].deck.size() && ((CardClass) (tableauD [i].deck.elementAt (j))).isPointInside (e.getX (), e.getY ()) && !tableauD [i].isDeckEmpty ())
                {
                    for (int k = j ; k < temp ; k++)
                    {
                        dragPile.addCard (tableauD [i].dealCard (j));
                    }
                    takenFromDeck = tableauD [i];
                    OKtoMove = true;
                }
            }
        }


        if (wasteD.isPointInside (e.getX (), e.getY ()) && !wasteD.isDeckEmpty ())
        {
            chooseCard (wasteD);
            OKtoMoveWaste = true;
            wasteClicked = true;
        }


        repaint ();
    }


    public void mouseReleased (MouseEvent e)
    {
        if ((!OKtoMove && !OKtoMoveWaste) || dragPile == null || dragPile.isDeckEmpty ())
        {
            return;
        }

        OKtoMove = false;
        OKtoMoveWaste = false;

        boolean moved = false;


        for (int i = 0 ; i < 8 ; i++) //placing on foundation -- not yet checked if valid yet
        {
            if (foundationD [i].isPointInside (e.getX (), e.getY ()))
            {
                if (validMove (foundationD [i], 'f'))
                {
                    foundationD [i].deck.addAll (dragPile.deck);
                    dragPile = new DeckClass ();
                    moved = true;
                }
            }
        }


        for (int i = 0 ; i < 12 ; i++)
        {
            if (tableauD [i].isPointInside (e.getX (), e.getY ()))
            {
                if (validMove (tableauD [i], 't'))
                {
                    tableauD [i].deck.addAll (dragPile.deck);

                    dragPile = new DeckClass ();
                    moved = true;
                }
            }
        }

        if (!moved && wasteClicked == false)
        {
            takenFromDeck.deck.addAll (dragPile.deck);

            dragPile = new DeckClass ();
        }



        if (!moved && wasteClicked == true)
        {
            wasteD.addCard (dragPile.dealCard (0));
            dragPile = new DeckClass ();
            wasteD.setCentre (900, 50);
            wasteClicked = false;
        }

        wasteClicked = false;

        repaint ();

    }


    public void mouseDragged (MouseEvent e)
    {
        if (OKtoMove || OKtoMoveWaste)
        {
            dragPile.setCentre (e.getX () - (dragPile.iWidth / 2), e.getY () - (dragPile.iHeight / 2));

            repaint ();
        }
    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public boolean checkWin ()
    {
        for (int i = 0 ; i < 8 ; i++)
        {
            if (!foundationD [i].isDeckEmpty () && (foundationD [i].lastCard ()).faceValue == 'K')
            {
                winFlag = true;
            }
            else
            {
                winFlag = false;
                return false;
            }
        }
        return winFlag;
    }


    public boolean checkLose ()
    {
        if (((endTime - startTime) / 1000) > 300)
        {
            loseFlag = true;
        }
        return loseFlag;
    }


    public void paint (Graphics g)
    {

        if (dragPile.deck.size () > 0 && (OKtoMove == true || OKtoMoveWaste == true))
        {
            dragPile.fanOut ("down", g);
        }


        for (int i = 0 ; i < 8 ; i++)
        {
            foundationD [i].draw (g, true);
        }


        for (int i = 0 ; i < 12 ; i++)
        {
            tableauD [i].fanOut ("down", g);

        }


        drawD.draw (g);
        wasteD.draw (g);


        endTime = System.currentTimeMillis ();
        time.setText (String.valueOf ((endTime - startTime) / 1000)+ " seconds");

        if (checkWin ())
        {
            g.setFont (font);
            g.drawString ("Winner", 100, 500);
        }

        if (checkLose ())
        {
            g.setFont (font);
            g.drawString ("Time is up. You lost :(", 50, 500);
        }


        // body of the drawing method here
    } // paint method
} // GameApplet class


