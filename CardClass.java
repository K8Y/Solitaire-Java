import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class CardClass extends ShapeClass
{

    protected char[] faceValueArray = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
    protected char faceValue = faceValueArray [0]; //[(int) ((Math.random () * 13) + 1)];
    protected boolean faceUp = true;
    protected int size = 1;
    protected int cSuit = 1;


    BufferedImage img = null;

    public CardClass ()
    {
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public CardClass (int suit, int value)  //instansiate suit and face value
    {
	setSuit (suit);
	setFaceValue (value);
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public CardClass (int suit, int value, int x, int y)
    {
	setSuit (suit);
	setFaceValue (value);
	setCentre (x, y);
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public CardClass (boolean face, int x, int y)  //set centre and face up
    {
	setFaceUp (face);
	setCentre (x, y);
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public CardClass (int suit, int value, boolean face)  //face is true when card is face up
    {
	setSuit (suit);
	setFaceValue (value);
	setFaceUp (face);
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public CardClass (int suit, int value, int x, int y, boolean face)  //face is true when card is face up
    {
	setSuit (suit);
	setFaceValue (value);
	setFaceUp (face);
	//setCentre (x, y);
	iCentreX = x;
	iCentreY = y;
	try
	{
	    img = ImageIO.read (new File ("cards\\" + cardName ()));
	}
	catch (IOException e)
	{
	}
    }


    public void flip ()  //flip card
    {
	if (faceUp)
	{
	    faceUp = false;
	}
	else
	{
	    faceUp = true;
	}
    }


    public void flip (CardClass card)  //flip card
    {
	if (card.faceUp)
	{
	    card.faceUp = false;
	}
	else
	{
	    card.faceUp = true;
	}
    }


    public void setFaceUp (boolean up)
    {
	faceUp = up;
    }


    public boolean getFaceUp ()
    {
	return faceUp;
    }


    public String cardName ()
    {
	String nSuit = new String ();


	switch (cSuit)
	{
	    case 1:
		nSuit = "d";
		break;
	    case 2:
		nSuit = "c";
		break;
	    case 3:
		nSuit = "h";
		break;
	    case 4:
		nSuit = "s";
		break;
	}


	nSuit = faceValue + nSuit + ".gif";
	if (faceUp)
	{
	    return nSuit;
	}
	else
	{
	    return "b.gif";
	}
    }



    public void roundToSizes (int ipHeight)  //round to the preset sizes
    {
	if (ipHeight / 120 != 0)
	{
	    iHeight = 120;
	    size = 4;
	}
	else if (ipHeight / 100 != 0)
	{
	    iHeight = 100;
	    size = 3;
	}
	else if (ipHeight / 80 != 0)
	{
	    iHeight = 80;
	    size = 2;
	}
	else
	{
	    iHeight = 60;
	    size = 1;
	}
    }


    public void setHeight (int ipHeight)
    {
	roundToSizes (ipHeight);
	super.setHeight (iHeight);
	super.setWidth ((iHeight / 10) * 7);
    }


    public void setWidth (int ipWidth)
    {
	roundToSizes ((ipWidth / 7) * 10);
	super.setHeight (iHeight);
	super.setWidth ((iHeight / 10) * 7);
    }


    public void setSuit (int s)
    {
	if (s >= 1 && s <= 4)
	{
	    cSuit = s;
	}
	else
	{
	    cSuit = (int) ((Math.random () * 4) + 1);
	}
    }


    public int getSuit ()
    {
	return cSuit;
    }


    public void setFaceValue (int v)
    {
	if (v >= 0 && v < 13)
	{
	    faceValue = faceValueArray [v];
	}
	else
	{
	    faceValue = faceValueArray [(int) ((Math.random () * 12) + 1)];
	}
    }


    public int getFaceValue ()
    {
	return faceValue;
    }



    public void draw (Graphics g)
    {
	toImgPos ();
	g.drawImage (img, iCentreX, iCentreY, null);

    }


    public void draw (Graphics g, boolean empty)
    {
	if (empty)
	{
	    try
	    {
		img = ImageIO.read (new File ("cards\\" + "empty.gif"));
	    }
	    catch (IOException e)
	    {
	    }
	    g.drawImage (img, iCentreX, iCentreY, null);
	}
    }


    public boolean isPointInside (int x, int y)
    {
	if (x < iCentreX + iWidth && x >= iCentreX && y < iCentreY + iHeight && y >= iCentreY)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public int returnValue ()
    {
	return faceValue;
    }


    public int returnSuit ()
    {
	return cSuit;
    }


    public int imgCentreX ()
    {
	return iCentreX - iWidth / 2;
    }


    public int imgCentreY ()
    {
	return iCentreY - iHeight / 2;
    }


    public void toImgPos ()
    {
	setCentre (imgCentreX (), imgCentreY ());
    }
}


