import java.awt.*;
import java.awt.event.*;

class AWTCalculatorGUI extends Frame implements ActionListener {

    // declare's components
    private TextField num1Field, num2Field, resultField;
    private Button addButton, subButton, mulButton, divButton, clearButton;

    public AWTCalculatorGUI() 
    {
        setTitle("AWT Calculator");
        setSize(400,300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // center window
        setResizable(false);

        // header
        Label header=new Label("Simple Arithmetic Calculator",Label.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        // input panel
        Panel inputPanel=new Panel(new GridLayout(3,2,10,10));
        inputPanel.setBackground(new Color(240,240,240));
        inputPanel.setFont(new Font("Arial",Font.PLAIN,14));
        inputPanel.setBounds(20, 50, 360, 100);

        inputPanel.add(new Label("Enter Number 1 :"));
        num1Field=new TextField();
        inputPanel.add(num1Field);

        inputPanel.add(new Label("Enter Number 2 :"));
        num2Field=new TextField();
        inputPanel.add(num2Field);

        inputPanel.add(new Label("Result :"));
        resultField=new TextField();
        resultField.setEditable(false);
        inputPanel.add(resultField);

        add(inputPanel,BorderLayout.CENTER);

        // button panel
        Panel buttonPanel=new Panel(new FlowLayout());
        addButton=new Button("+");
        subButton=new Button("-");
        mulButton=new Button("*");
        divButton=new Button("/");
        clearButton=new Button("Clear");

        // style buttons
        Font btnFont=new Font("Arial",Font.BOLD,14);
        Button[] buttons={addButton,subButton,mulButton,divButton,clearButton};
        for (Button b : buttons) 
        {
            b.setFont(btnFont);
            b.setPreferredSize(new Dimension(60,30));
            b.addActionListener(this);
            buttonPanel.add(b);
        }

        add(buttonPanel,BorderLayout.SOUTH);

        // window close
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                dispose();
            }
        });

        setVisible(true);
    }

    // handle operations
    public void actionPerformed(ActionEvent e) 
    {
        try 
        {
            String text1=num1Field.getText().trim();
            String text2=num2Field.getText().trim();

            if (text1.isEmpty() || text2.isEmpty()) 
            {
                showError("Please enter both numbers.");
                return;
            }

            double num1=Double.parseDouble(text1);
            double num2=Double.parseDouble(text2);
            double result=0;

            if (e.getSource()==addButton) 
            {
                result=num1+num2;
            } 
            else if (e.getSource()==subButton) 
            {
                result=num1-num2;
            } 
            else if (e.getSource()==mulButton) 
            {
                result=num1*num2;
            } 
            else if (e.getSource()==divButton) 
            {
                if (num2 == 0) 
                {
                    showError("Cannot divide by zero.");
                    return;
                }
                result=num1/num2;
            } 
            else if (e.getSource()==clearButton) 
            {
                num1Field.setText("");
                num2Field.setText("");
                resultField.setText("");
                return;
            }

            resultField.setText(String.format("%.2f",result));

        } 
        catch (NumberFormatException ex) 
        {
            showError("Invalid input! Please enter numeric values.");
        }
    }

    // error message dialog
    private void showError(String message) 
    {
        Dialog d = new Dialog(this, "Error", true);
        d.setLayout(new FlowLayout());
        Label msg = new Label(message);
        msg.setFont(new Font("Arial", Font.PLAIN, 13));
        d.add(msg);
        Button ok = new Button("OK");
        ok.addActionListener(e -> d.setVisible(false));
        d.add(ok);
        d.setSize(300, 100);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    public static void main(String[] args) 
    {
        new AWTCalculatorGUI();
    }
}
