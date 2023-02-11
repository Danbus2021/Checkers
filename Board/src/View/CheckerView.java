package View;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckerView extends JComponent {
    private Checker checker;
    public CheckerView(Checker c){
        checker = c;
    }
    public Checker getChecker(){
        return checker;
    }
}
