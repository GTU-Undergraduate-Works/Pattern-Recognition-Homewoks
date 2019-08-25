import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Snake extends JFrame implements ActionListener{

    private BoardPanel boardPanel;
    private Timer timer;

    public Snake() {

        initUI();
        timer = new Timer(50, this);
        timer.start();
    }

    private void initUI() {


        boardPanel = new BoardPanel();
        add(boardPanel);

        setResizable(false);
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        setTitle("G : " + boardPanel.getGeneration() +  " S : "  + boardPanel.getScore());
    }
}