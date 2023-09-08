/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

/**
 *
 * @author Thanh Tum
 */
public class QLCamera {

    private String id;
    private String ma;
    private String ten;
    private int stt;

    public QLCamera() {
    }

    public QLCamera(String id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }



    public QLCamera(String ma, String ten, int stt) {
        this.ma = ma;
        this.ten = ten;
        this.stt = stt;
    }

    public QLCamera(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    
}
