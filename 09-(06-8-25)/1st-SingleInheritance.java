// Plant -> Flower

class Plant 
{
    void grow() 
    {
        System.out.println("The plant is growing using sunlight .");
    }
}

class Flower extends Plant 
{
    void bloom() 
    {
        System.out.println("The flower is blooming with colors .");
    }
}

class SingleInheritance 
{
    public static void main(String[] args) 
    {
        Flower f=new Flower();
        f.grow();
        f.bloom();
    }
}


// output example

/* 
The plant is growing using sunlight .
The flower is blooming with colors . 
*/