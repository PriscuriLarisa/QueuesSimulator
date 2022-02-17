package queueSimulator.view;

import javax.swing.*;
import java.util.List;

public class MyWorker extends SwingWorker<String, String> {
    private JTextArea textArea;
    private String data;

    public MyWorker(String s, JTextArea textArea) {
        this.textArea = textArea;
        this.data = s;
    }

    @Override
    protected String doInBackground() throws Exception {
        publish(data);
        return data;
    }

    @Override
    protected void process(List<String> chunks) {

        String s = chunks.get(chunks.size()-1);
        textArea.setText(s);
    }
}

