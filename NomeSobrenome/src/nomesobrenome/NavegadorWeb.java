/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomesobrenome;
//package NavegadorWeb;  
  
/** 
* ***************************************************************************** 
* Copyright (c) 2004 IBM Corporation. All rights reserved. This program and the 
* accompanying materials are made available under the terms of the Common 
* Public License v1.0 which accompanies this distribution, and is available at 
* http://www.eclipse.org/legal/cpl-v10.html 
****************************************************************************** 
*/  

import org.eclipse.swt.SWT;  
import org.eclipse.swt.browser.Browser;  
import org.eclipse.swt.browser.CloseWindowListener;  
import org.eclipse.swt.browser.LocationEvent;  
import org.eclipse.swt.browser.LocationListener;  
import org.eclipse.swt.browser.ProgressEvent;  
import org.eclipse.swt.browser.ProgressListener;  
import org.eclipse.swt.browser.StatusTextEvent;  
import org.eclipse.swt.browser.StatusTextListener;  
import org.eclipse.swt.browser.WindowEvent;  
import org.eclipse.swt.events.SelectionAdapter;  
import org.eclipse.swt.events.SelectionEvent;  
import org.eclipse.swt.layout.FormAttachment;  
import org.eclipse.swt.layout.FormData;  
import org.eclipse.swt.layout.FormLayout;  
import org.eclipse.swt.layout.GridData;  
import org.eclipse.swt.layout.GridLayout;  
import org.eclipse.swt.widgets.Button;  
import org.eclipse.swt.widgets.Composite;  
import org.eclipse.swt.widgets.Display;  
import org.eclipse.swt.widgets.Label;  
import org.eclipse.swt.widgets.Shell;  
import org.eclipse.swt.widgets.Text;  
  
public class Navegador {  
  
    private static final String AT_REST = "...";  
  
    public Navegador(String location) {  
        Display display = new Display();  
        Shell shell = new Shell(display);  
        shell.setText("Navegador Internet");  
  
        shell.setLayout(new FormLayout());  
  
        Composite controls = new Composite(shell, SWT.NONE);  
        FormData data = new FormData();  
        data.top = new FormAttachment(0, 0);  
        data.left = new FormAttachment(0, 0);  
        data.right = new FormAttachment(100, 0);  
        controls.setLayoutData(data);  
  
        Label status = new Label(shell, SWT.NONE);  
        data = new FormData();  
        data.left = new FormAttachment(0, 0);  
        data.right = new FormAttachment(100, 0);  
        data.bottom = new FormAttachment(100, 0);  
        status.setLayoutData(data);  
  
        final Browser browser = new Browser(shell, SWT.BORDER);  
        data = new FormData();  
        data.top = new FormAttachment(controls);  
        data.bottom = new FormAttachment(status);  
        data.left = new FormAttachment(0, 0);  
        data.right = new FormAttachment(100, 0);  
        browser.setLayoutData(data);  
  
        controls.setLayout(new GridLayout(7, false));  
  
        Button button = new Button(controls, SWT.PUSH);  
        button.setText("<");  
        button.addSelectionListener(new SelectionAdapter() {  
  
            public void widgetSelected(SelectionEvent event) {  
                browser.back();  
            }  
        });  
  
        button = new Button(controls, SWT.PUSH);  
        button.setText(">");  
        button.addSelectionListener(new SelectionAdapter() {  
  
            public void widgetSelected(SelectionEvent event) {  
                browser.forward();  
            }  
        });  
  
        button = new Button(controls, SWT.PUSH);  
        button.setText("Atualizar");  
        button.addSelectionListener(new SelectionAdapter() {  
  
            public void widgetSelected(SelectionEvent event) {  
                browser.refresh();  
            }  
        });  
  
        button = new Button(controls, SWT.PUSH);  
        button.setText("Parar");  
        button.addSelectionListener(new SelectionAdapter() {  
  
            public void widgetSelected(SelectionEvent event) {  
                browser.stop();  
            }  
        });  
  
        final Text url = new Text(controls, SWT.BORDER);  
        url.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));  
        url.setFocus();  
  
        button = new Button(controls, SWT.PUSH);  
        button.setText("Ir");  
        button.addSelectionListener(new SelectionAdapter() {  
  
            public void widgetSelected(SelectionEvent event) {  
                browser.setUrl(url.getText());  
            }  
        });  
  
        Label throbber = new Label(controls, SWT.NONE);  
        throbber.setText(AT_REST);  
  
        shell.setDefaultButton(button);  
  
        browser.addCloseWindowListener(new AdvancedCloseWindowListener());  
        browser.addLocationListener(new AdvancedLocationListener(url));  
        browser.addProgressListener(new AdvancedProgressListener(throbber));  
        browser.addStatusTextListener(new AdvancedStatusTextListener(status));  
  
        // Go to the initial URL    
        if (location != null) {  
            browser.setUrl(location);  
        }  
  
  
        shell.open();  
        shell.forceFocus();  
        shell.setActive();  
        shell.forceActive();  
        while (!shell.isDisposed()) {  
            if (!display.readAndDispatch()) {  
                display.sleep();  
            }  
        }  
        display.dispose();  
    }  
  
    class AdvancedCloseWindowListener implements CloseWindowListener {  
  
        public void close(WindowEvent event) {  
            ((Browser) event.widget).getShell().close();  
        }  
    }  
  
    class AdvancedLocationListener implements LocationListener {  
  
        private Text location;  
  
        public AdvancedLocationListener(Text text) {  
            location = text;  
        }  
  
        public void changing(LocationEvent event) {  
            location.setText("Carregando " + event.location + "...");  
        }  
  
        public void changed(LocationEvent event) {  
            location.setText(event.location);  
        }  
    }  
  
    class AdvancedProgressListener implements ProgressListener {  
  
        private Label progress;  
  
        public AdvancedProgressListener(Label label) {  
            progress = label;  
        }  
  
        public void changed(ProgressEvent event) {  
            if (event.total != 0) {  
                int percent = (int) (event.current / event.total);  
                progress.setText(percent + "%");  
            } else {  
                progress.setText("?");  
            }  
        }  
  
        public void completed(ProgressEvent event) {  
            progress.setText(AT_REST);  
        }  
    }  
  
    class AdvancedStatusTextListener implements StatusTextListener {  
  
        private Label status;  
  
        public AdvancedStatusTextListener(Label label) {  
            status = label;  
        }  
  
        public void changed(StatusTextEvent event) {  
            status.setText(event.text);  
        }  
    }  
}  