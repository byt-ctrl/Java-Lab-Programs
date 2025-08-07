// Tool -> CuttingTool -> Scissor

class Tool 
{
    void use() 
    {
        System.out.println("Tool is used for work.");
    }
}

class CuttingTool extends Tool 
{
    void sharpen() 
    {
        System.out.println("Cutting tool is being sharpened.");
    }
}

class Scissor extends CuttingTool 
{
    void cut() 
    {
        System.out.println("Scissor is cutting paper.");
    }
}

class MultilevelInheritance 
{
    public static void main(String[] args) 
    {
        Scissor s=new Scissor();
        s.use();
        s.sharpen();
        s.cut();
    }
}

// output example
/*
Tool is used for work.
Cutting tool is being sharpened.
Scissor is cutting paper.
*/
