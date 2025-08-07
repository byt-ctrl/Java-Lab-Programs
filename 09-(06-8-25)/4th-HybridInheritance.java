// Painter (abstract class) + Creative and Skilled (interfaces)

interface Creative 
{
    void imagine();
}

interface Skilled 
{
    void practice();
}

abstract class Painter 
{
    void paint() 
    {
        System.out.println("Painter is creating an artwork.");
    }
}

class MuralArtist extends Painter implements Creative,Skilled 
{
    public void imagine() 
    {
        System.out.println("Imagining a large mural design.");
    }

    public void practice() 
    {
        System.out.println("Practicing brush strokes daily.");
    }
}

class HybridInheritance 
{
    public static void main(String[] args) 
    {
        MuralArtist artist=new MuralArtist();
        artist.paint();
        artist.imagine();
        artist.practice();
    }
}



// output example
/*
Painter is creating an artwork.
Imagining a large mural design.
Practicing brush strokes daily.
*/
