package com.npuMa.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ClothesDialog extends Dialog {
	  private String message;
	  private List<String> input = new ArrayList<String>();
	  private Shell shell_1;
	  private GridData gd_textKind;
	  public ClothesDialog(Shell parent) {
	    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	  }
	  public ClothesDialog(Shell parent, int style) {
	    super(parent, style);
	    setText("Clothes Dialog");
	    setMessage("Input kind of clothes, price and customer ID:");
	  }
	  public String getMessage() {
	    return message;
	  }
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  public List<String> getInput() {
	    return input;
	  }
	  public void setInput(List<String> input) {
	    this.input = input;
	  }
	  public List<String> open() {
	    shell_1 = new Shell(getParent(), getStyle());
	    shell_1.setSize(277, 214);
	    shell_1.setText(getText());
	    createContents(shell_1);
	    shell_1.pack();
	    new Label(shell_1, SWT.NONE);
	    shell_1.open();
	    Display display = getParent().getDisplay();
	    while (!shell_1.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    return input;
	  }
	  private void createContents(final Shell shell) {
	    shell.setLayout(new GridLayout(3, true));
	    Label label = new Label(shell, SWT.NONE);
	    label.setText(message);
	    GridData data = new GridData();
	    data.horizontalSpan = 2;
	    label.setLayoutData(data);
	    new Label(shell_1, SWT.NONE);
	    final Text textKind = new Text(shell, SWT.BORDER);
	    gd_textKind = new GridData(GridData.FILL_HORIZONTAL);
	    textKind.setLayoutData(gd_textKind);
	    final Text textPrice = new Text(shell, SWT.BORDER);
	    textPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    final Text textCustID = new Text(shell, SWT.BORDER);
	    textCustID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    
	    if(!input.isEmpty()) {
		    textKind.setText(input.get(0));
		    textPrice.setText(input.get(1));
		    textCustID.setText(input.get(2));
	    }

	    Button ok = new Button(shell, SWT.PUSH);
	    ok.setText("OK");
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    ok.setLayoutData(data);
	    ok.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	    	input.clear();
	    	input.add(textKind.getText());
	        input.add(textPrice.getText());
	        input.add(textCustID.getText());
	        shell.close();
	      }
	    });
	    Button cancel = new Button(shell, SWT.PUSH);
	    cancel.setText("Cancel");
	    data = new GridData(GridData.FILL_HORIZONTAL);
	    cancel.setLayoutData(data);
	    cancel.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        input.clear();
	        shell.close();
	      }
	    });
	    shell.setDefaultButton(ok);
	  }
	}

