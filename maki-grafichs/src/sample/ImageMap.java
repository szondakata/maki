package sample;

public class ImageMap
{
    int[][] kep;//értéke a szín

    ImageMap(int x_meret,int y_meret)
    {
        kep = new int[x_meret][y_meret];
    }


    public void setRGB(int x, int y, int c) {
        kep[x][y] = c;
    }

    public int getRGB(int x, int y) {
        return kep[x][y];
    }
}
