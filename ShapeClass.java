import java.awt.*;

public abstract class ShapeClass
{
    protected int iWidth = 80;
    protected int iHeight = 100;
    protected int iCentreX = 300;
    protected int iCentreY = 100;
    protected Color cColor = Color.cyan;
    protected boolean filled = true;

    public void setWidth (int iNewWidth)
    {
	iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
	iHeight = iNewHeight;
    }


    public void setCentre (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }


    public void setColor (Color cNewColor)
    {
	// if (cNewColor == Color.red || cNewColor == Color.black || cNewColor == Color.white)
	// {
	cColor = cNewColor;
	// }
	// else
	// {
	//     cColor = Color.red;
	// }
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return iCentreX;
    }


    public int getCenterY ()
    {
	return iCentreY;
    }


    public Color getColor ()
    {
	return cColor;
    }


    public void setIsFilled (boolean newfilled)
    {
	filled = newfilled;
    }


    public boolean getIsFilled ()
    {
	return filled;
    }


    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }


    public abstract void draw (Graphics g);


    public void erase (Graphics g)
    {
	Color cOldColor = cColor;
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }


    public boolean isPointInside (int x, int y)
    {
	return (x <= iCentreX + (iWidth / 2) && x >= iCentreX - (iWidth / 2) && y <= iCentreY + (iHeight / 2) && y >= iCentreY - (iHeight / 2));
    }
}
