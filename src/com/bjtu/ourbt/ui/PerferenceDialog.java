package com.bjtu.ourbt.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.bjtu.ourbt.ui.listener.SelectionButtonListener;

public class PerferenceDialog extends Dialog {
    private Shell shell;
    private Object result;

    public PerferenceDialog(Shell shell) {
        this(shell, SWT.NONE);
    }

    public PerferenceDialog(Shell shell, int style) {
        super(shell, style);
    }

    public Object open() {
        createContent();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        return result;
    }

    private void createContent() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM);
        shell.setBounds(400, 180, 600, 460);
        shell.setText("Perferences");
        shell.setLayout(new FillLayout(SWT.VERTICAL));
        SashForm sashForm = new SashForm(shell, SWT.SEPARATOR | SWT.VERTICAL);

        SashForm childSashForm = new SashForm(sashForm, SWT.NONE);
        OptionTree optionTree = new OptionTree(childSashForm);
        optionTree.open();

        Composite composite = new Composite(sashForm, SWT.OK);
        Button okButton = new Button(composite, SWT.NONE);
        okButton.setText("OK");
        okButton.setBounds(400, 5, 50, 25);
        okButton.addListener(SWT.Selection, new SelectionButtonListener(shell));

        Button cancelButton = new Button(composite, SWT.CLOSE);
        cancelButton.setText("Cancel");
        cancelButton.setBounds(460, 5, 50, 25);
        cancelButton.addListener(SWT.Selection, new SelectionButtonListener(
                shell));

        Button applyButton = new Button(composite, SWT.NONE);
        applyButton.setText("Apply");
        applyButton.setBounds(520, 5, 50, 25);
        applyButton.addListener(SWT.Selection, new SelectionButtonListener(
                shell));

        sashForm.setWeights(new int[] { 90, 10 });
        sashForm.setLayout(new FillLayout());
    }

}
