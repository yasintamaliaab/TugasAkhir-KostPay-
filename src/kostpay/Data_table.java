/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kostpay;

public class Data_table {
    private String nm_user;
    private String bln;
    private String code1;
    private String bayar1;
    public Data_table(String nm_user, String bln, String code1, String bayar1) {
        this.nm_user = nm_user;
        this.bln = bln;
        this.code1 = code1;
        this.bayar1 = bayar1;}
    public String getNm_user() {
        return nm_user;}
    public void setNm_user(String nm_user) {
        this.nm_user = nm_user;}
    public String getBln() {
        return bln;}
    public void setBln(String bln) {
        this.bln = bln;}
    public String getCode1() {
        return code1;}
    public void setCode1(String code1) {
        this.code1 = code1;}
    public String getBayar1() {
        return bayar1;}
    public void setBayar1(String bayar1) {
        this.bayar1 = bayar1;}}
