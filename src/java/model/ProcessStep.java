/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HKDUC
 */
public class ProcessStep {
    int ProcessStepNo;
    String ProcessStep;

    public ProcessStep(int ProcessStepNo, String ProcessStep) {
        this.ProcessStepNo = ProcessStepNo;
        this.ProcessStep = ProcessStep;
    }

    public ProcessStep() {
    }

    public int getProcessStepNo() {
        return ProcessStepNo;
    }

    public void setProcessStepNo(int ProcessStepNo) {
        this.ProcessStepNo = ProcessStepNo;
    }

    public String getProcessStep() {
        return ProcessStep;
    }

    public void setProcessStep(String ProcessStep) {
        this.ProcessStep = ProcessStep;
    }
    
    
}
