package com.mi.robodstar.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Console implements Runnable {
    static JFrame frame;
    JTextArea displayPane;
    BufferedReader reader;
    static boolean seen = false;

    private Console(JTextArea displayPane, PipedOutputStream pos) {
        this.displayPane = displayPane;

        try {
            PipedInputStream pis = new PipedInputStream( pos );
            reader = new BufferedReader( new InputStreamReader(pis) );
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String line;

        try {
            while ((line = reader.readLine()) != null) {
//              displayPane.replaceSelection( line + "\n" );
                displayPane.append( line + "\n" );
                displayPane.setCaretPosition( displayPane.getDocument().getLength() );
            }

            System.err.println("im here");
        }
        catch (IOException ioe) {
            Console.redirectOutput(displayPane);
//            JOptionPane.showMessageDialog(null,
//                    "Error redirecting output : " + ioe.getMessage());
        }
    }

    public static void redirectOutput(JTextArea displayPane) {
        Console.redirectOut(displayPane);
        Console.redirectErr(displayPane);
    }

    public static void redirectOut(JTextArea displayPane) {
        PipedOutputStream pos = new PipedOutputStream();
        System.setOut(new PrintStream(pos, true));

        Console console = new Console(displayPane, pos);
        new Thread(console).start();
    }

    public static void redirectErr(JTextArea displayPane) {
        PipedOutputStream pos = new PipedOutputStream();
        System.setErr(new PrintStream(pos, true));

        Console console = new Console(displayPane, pos);
        new Thread(console).start();
    }

    public static void showConsole(){
        if(!seen) {
            frame = new JFrame("Console");
            frame.setLayout(new BorderLayout());

            JPanel f = new JPanel();


            f.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );
            f.setLayout(new BorderLayout(5, 5));
            frame.add(f, BorderLayout.CENTER);

            JTextArea textArea = new JTextArea();
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN, 12));
            textArea.setFocusable(false);
            frame.setLocation(20, 20);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JPanel buttonPane = new JPanel(new BorderLayout());
            JButton clear = new JButton("Clear console");
            clear.addActionListener(new clear(textArea));
            buttonPane.add(clear, BorderLayout.WEST);

            f.add(scrollPane, BorderLayout.CENTER);
            f.add(buttonPane, BorderLayout.SOUTH);
            frame.addWindowListener(new close(frame));

            frame.getContentPane().add(f);
            frame.setSize(400, 300);
            seen = true;
            frame.setVisible(true);

            Console.redirectOutput(textArea);
            textArea.setText("INFO: Console opened\n");
        }
    }

    public static void grabFocus() {
        frame.requestFocus();
    }

    private static class clear implements ActionListener {
        JTextArea text;

        public clear (JTextArea t) {
            text = t;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            text.setText("");
        }
    }

    private static class close extends WindowAdapter {
        JFrame frame;

        public close(JFrame th){
            frame = th;
        }

        @Override
        public void windowClosing(WindowEvent e){
            frame.dispose();
            seen = false;
        }
    }
}
