import java.awt.*;
import java.awt.event.*;

class HelloWorldAWT {
    public static void main(String[] args) 
    {
        // create's a Frame
        Frame frame = new Frame("AWT Hello World ");

        // create's a Button
        Button button = new Button("Hello World");

        // set button position and size
        button.setBounds(100, 100, 100, 40);

        // add action listener to the button
        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World");
                // show's a dialog (uncomment if needed) till after 5 lines
                Dialog d=new Dialog(frame, "Message");
                d.setSize(200,100);
                d.setLayout(new FlowLayout());
                d.add(new Label("Hello World !"));
                d.setVisible(true);
            }
        });

        // add button to frame
        frame.add(button);

        // frame settings
        frame.setSize(300,250);
        frame.setLayout(null);
        frame.setVisible(true);

        // add window closing functionality
        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent we) 
            {
                frame.dispose();
            }
        });
    }
}
