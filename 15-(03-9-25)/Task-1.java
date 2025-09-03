/*-------------------------------------------------------------
 | Name               : Patel Om Bhavinkumar
 | Enrolment No.      : 24000112
 | Section            : A
 | Date               : [03-09-25]
 | Program Definition :  1. Create a Java AWT calculator with GridLayout and BorderLayout, supporting basic arithmetic, square root, power, percentage, inverse, and sign toggle, with Clear, Clear Entry, and Backspace buttons. 
							    Use a TextField with AWT event listeners, ensuring a clean UI with proper fonts, spacing, and error handling for floating-point inputs. 
							   	Add keyboard input and history panel. Provide commented source code and compilation instructions.
 -------------------------------------------------------------*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Calculator extends Frame implements ActionListener, KeyListener 
{

    private TextField display;
    private List historyList;

    // calculator's state
    private double memory=0.0;
    private double lastNumber=0.0;
    private String operator="";
    private boolean isNewInput=true;
    private boolean decimalPressed=false;

    // number buttons (0-9)
    private Button[] numButtons=new Button[10];

    // operator buttons
    private Button addButton , subButton , mulButton , divButton , equalsButton;
    private Button sqrtButton , powButton , percentButton , inverseButton , signButton;
    private Button clearButton , clearEntryButton , backspaceButton;

    // memory buttons
    private Button memClearButton , memRecallButton , memPlusButton , memMinusButton;

    // history
	// help from AI (to know how to make history)
    private ArrayList<String> expressionHistory;



    public Calculator() {
        // initialize history
        expressionHistory=new ArrayList<>();

        // frame setup
        setTitle("Calculator");
        setSize( 700,700);
        setLayout(new BorderLayout(5, 5));

        // close on exit
        addWindowListener(new WindowAdapter() 
		{
            public void windowClosing(WindowEvent e) 
			{
                System.exit(0);
            }
        });

        // enable keyboard focus
        setFocusable(true);
        addKeyListener(this);

        // ======= display Field =======
        display=new TextField("0");
        display.setEditable(false);
        display.setFont(new Font("Dialog",Font.PLAIN,20));
        add(display,BorderLayout.NORTH);

        // ======= button panel (6*5 Grid) =======
        Panel buttonPanel=new Panel(new GridLayout(6,5,3,3));

        String[][] labels= 
		{
            {"MC", "MR", "M+", "M-", "C"},
            {"CE", "←", "√", "x^y", "%"},
            {"7", "8", "9", "+", "1/x"},
            {"4", "5", "6", "-", "±"},
            {"1", "2", "3", "*", "="},
            {"0", "00", ".", "/", ""}
        };

        for (String[] row : labels) 
		{
            for (String lbl : row) 
			{
                if (lbl.isEmpty()) continue;

                Button btn=new Button(lbl);
                btn.addActionListener(this);
                btn.addKeyListener(this);
                buttonPanel.add(btn);

                // assign number buttons
                if (lbl.matches("[0-9]")) 
				{
                    int num=Integer.parseInt(lbl);
                    numButtons[num]=btn;
                } 
				else 
				{
                    // assign special buttons
                    switch (lbl) 
					{
                        case "+": addButton = btn; break;
                        case "-": subButton = btn; break;
                        case "*": mulButton = btn; break;
                        case "/": divButton = btn; break;
                        case "=": equalsButton = btn; break;
                        case "√": sqrtButton = btn; break;
                        case "x^y": powButton = btn; break;
                        case "%": percentButton = btn; break;
                        case "1/x": inverseButton = btn; break;
                        case "±": signButton = btn; break;
                        case "←": backspaceButton = btn; break;
                        case "C": clearButton = btn; break;
                        case "CE": clearEntryButton = btn; break;
                        case "MC": memClearButton = btn; break;
                        case "MR": memRecallButton = btn; break;
                        case "M+": memPlusButton = btn; break;
                        case "M-": memMinusButton = btn; break;
                    }
                }
            }
        }

        add(buttonPanel, BorderLayout.CENTER);

        // ======= history Panel (Right) =======
		// just for refrence i have taken this conept from AI to implement in this calculater
        Panel historyPanel=new Panel(new BorderLayout());
        historyPanel.setPreferredSize(new Dimension(150,0));

        Label histLabel=new Label("History",Label.CENTER);
        historyList=new List(10);

        historyPanel.add(histLabel,BorderLayout.NORTH);
        historyPanel.add(historyList,BorderLayout.CENTER);
        add(historyPanel,BorderLayout.EAST);

        // ------- making frame visible ------------
        // finalizing layout and show
        pack();  // adjust's size based on components (optional , but safe)
        setLocationRelativeTo(null); // center on screen
        setVisible(true);  
    }

    @Override
    public void actionPerformed(ActionEvent e) 
	{
        String cmd = e.getActionCommand();

        // handle digits
        if (cmd.matches("[0-9]")) 
		{
            if (isNewInput) 
			{
                display.setText(cmd);
                isNewInput=false;
            } 
			else 
			{
                display.setText(display.getText() + cmd);
            }
            return;
        }

        // decimal point
        if (cmd.equals(".")) 
		{
            if (!decimalPressed) 
			{
                if (isNewInput) 
				{
                    display.setText("0.");
                    isNewInput=false;
                } 
				else 
				{
                    display.setText(display.getText() + ".");
                }
                decimalPressed=true;
            }
            return;
        }

        // clear entry
        if (cmd.equals("CE")) 
		{
            display.setText("0");
            isNewInput=true;
            decimalPressed=false;
            return;
        }

        // clear all logic
        if (cmd.equals("C")) 
		{
            display.setText("0");
            lastNumber=0;
            operator="";
            isNewInput=true;
            decimalPressed=false;
            return;
        }

        // backspace logic
        if (cmd.equals("←")) 
		{
            String text=display.getText();
            if (text.length() <= 1 || (text.length() == 2 && text.startsWith("-"))) 
			{
                display.setText("0");
                isNewInput=true;
            } 
			else 
			{
                display.setText(text.substring(0, text.length() - 1));
            }
            
			
			if (!display.getText().contains(".")) 
			{
                decimalPressed=false;
            }
            return;
        }

        // if we reach here , assume it's an operation that requires evaluation or state change

        // trying to parse current number
        double current;
        try 
		{
            current=Double.parseDouble(display.getText());
        } 
		catch (NumberFormatException ex) 
		{
            display.setText("Error");
            isNewInput=true;
            return;
        }

        // handle;s operators (+, -, *, /, x^y)
        if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/") || cmd.equals("x^y")) 
		{
            lastNumber=current;
            operator=cmd.equals("x^y") ? "^" : cmd;
            isNewInput=true;
            decimalPressed=false;
            return;
        }

        // equals operations
        if (cmd.equals("=")) 
		{
            if (operator.isEmpty()) return;

            double result;
            boolean error=false;
            String expr=lastNumber + " " + operator + " " + current;

            switch (operator) 
			{
                case "+":
                    result = lastNumber + current;
                    break;
                case "-":
                    result = lastNumber - current;
                    break;
                case "*":
                    result = lastNumber * current;
                    break;
                case "/":
                    if (current == 0) 
					{
                        display.setText("Error");
                        addToHistory(expr + " = Error (Div by 0)");
                        clearAll();
                        return;
                    }
                    result = lastNumber / current;
                    break;
                
				case "^":
                    result = Math.pow(lastNumber,current);
                    break;
                default:
                    return;
            }

            String resultStr = format(result);
            display.setText(resultStr);
            addToHistory(expr + " = " + resultStr);
            operator = "";
            isNewInput = true;
            decimalPressed = !isInteger(result);
            return;
        }

        //  functions
		// symbol of square root is taken from online for better visualization
        if (cmd.equals("√")) 
		{
            if (current < 0) 
			{
                display.setText("Error");
                addToHistory("√(" + current + ") = Error");
            } 
			else 
			{
                double r = Math.sqrt(current);
                display.setText(format(r));
                addToHistory("√(" + current + ") = " + format(r));
                isNewInput = true;
                decimalPressed = !isInteger(r);
            }
            return;
        }

        if (cmd.equals("%")) 
		{
            double r = current / 100.0;
            display.setText(format(r));
            addToHistory(current + "% = " + format(r));
            isNewInput = true;
            decimalPressed = !isInteger(r);
            return;
        }

        if (cmd.equals("1/x")) 
		{
            if (current == 0) 
			{
                display.setText("Error");
                addToHistory("1/" + current + " = Error");
            } 
			else 
			{
                double r = 1.0 / current;
                display.setText(format(r));
                addToHistory("1/" + current + " = " + format(r));
                isNewInput = true;
                decimalPressed = !isInteger(r);
            }
            return;
        }

        if (cmd.equals("±")) 
		{
            double r = -current;
            display.setText(format(r));
            isNewInput = true;
            decimalPressed = !isInteger(r);
            return;
        }

        // memory operations
        if (cmd.equals("MC")) 
		{
            memory = 0;
            addToHistory("Memory = 0");
        } 
		else if (cmd.equals("MR")) 
		{
            display.setText(format(memory));
            isNewInput = true;
        } 
		else if (cmd.equals("M+")) 
		{
            memory += current;
            addToHistory("M += " + current);
        } 
		else if (cmd.equals("M-")) 
		{
            memory -= current;
            addToHistory("M -= " + current);
        }
    }

    // format number: remove trailing decimal if whole
    private String format(double d) 
	{
        if (d == (long) d) 
		{
            return String.valueOf((long) d);
        } 
		else 
		{
            return String.valueOf(d);
        }
    }


    private boolean isInteger(double d) 
	{
        return d == (long) d;
    }
	
	

    private void addToHistory(String entry) 
	{
        expressionHistory.add(entry);
        historyList.add(entry);
        if (historyList.getItemCount() > 100) 
		{
            historyList.remove(0); // keep's history size limited
        }
    }

    private void clearAll() 
	{
        display.setText("0");
        lastNumber = 0;
        operator = "";
        isNewInput = true;
        decimalPressed = false;
    }

    // ======= KeyListener =======
    @Override
    public void keyPressed(KeyEvent e) 
	{
        char c = e.getKeyChar();
        int key = e.getKeyCode();

        if (c >= '0' && c <= '9') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(c));
            actionPerformed(fake);
        } 
		else if (c == '.') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ".");
            actionPerformed(fake);
        } 
		else if (c == '+') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "+");
            actionPerformed(fake);
        } 
		else if (c == '-') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "-");
            actionPerformed(fake);
        } 
		else if (c == '*') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "*");
            actionPerformed(fake);
        } 
		else if (c == '/') 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "/");
            actionPerformed(fake);
        } else if (key == KeyEvent.VK_ENTER || c == '=') {
            ActionEvent fake = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, "=");
            actionPerformed(fake);
        } 
		else if (key == KeyEvent.VK_BACK_SPACE) 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "←");
            actionPerformed(fake);
        } 
		else if (key == KeyEvent.VK_ESCAPE) 
		{
            ActionEvent fake = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "C");
            actionPerformed(fake);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    // =============== MAIN ===============
    public static void main(String[] args) {
        // run's in GUI thread (optional, but it helps in good practice)
        new Calculator();
    }
} 



// end of code 