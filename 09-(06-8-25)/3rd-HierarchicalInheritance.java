// Instrument -> Guitar , Piano

class Instrument 
{
    void play() 
    {
        System.out.println("Instrument is being played.");
    }
}

class Guitar extends Instrument 
{
    void strum() 
    {
        System.out.println("Strumming the guitar strings.");
    }
}

class Piano extends Instrument 
{
    void pressKeys() 
    {
        System.out.println("Pressing piano keys melodically.");
    }
}

class HierarchicalInheritance 
{
    
    public static void main(String[] args) 
    {
        Guitar g=new Guitar();
        g.play();
        g.strum();

        Piano p=new Piano();
        p.play();
        p.pressKeys();
    }
}

// output example
/*
Instrument is being played.
Strumming the guitar strings.
Instrument is being played.
Pressing piano keys melodically.
*/
