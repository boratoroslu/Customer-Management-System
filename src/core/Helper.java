package core;

import javax.swing.*;

public class Helper {
    public static void setTheme(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals("Nimbus")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fields ){
        for(JTextField field : fields){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }
    public static boolean isEmailValid(String mail){
        // info@gmail.com
        //@ olmalı , @'den önce bir değer olmalı , @'den sonra nokta (.) ile devam etmeli.
        //noktadan sonra değer ile devam etmeli
        if (mail==null || mail.trim().isEmpty()) return false;
        if (!mail.contains("@")) return false; //Contains string içinde istediğim karakter var mı diye kontrol eder.
        //mail için @ işareti yok ise false değer döndürür.

        String[] parts = mail.split("@");// girilen stringde @ işaretini bulucak ve ikiye bölecek
                                                // örn bora@gmail.com  --->  bora | gmail.com
        if (parts.length != 2) return false;

        if (parts[0].trim().isEmpty() || parts [1].trim().isEmpty()) return false;

        if (!parts[1].contains(".")) return false;
        return true;
    }
    public  static void optionPanrDialogTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

    public static void showMsg(String message){
       String msg;
       String title;

   optionPanrDialogTR();
        switch (message){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!";
                title ="HATA!";
                break;
            case "done":
                msg = "İşlem Başarılı";
                title ="Sonuç";
                break;
            case "error":
                msg = "Bir hata oluştu!";
                title ="HATA!";
                break;
            default:
                msg = message;
                title ="Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confrim(String str){
        optionPanrDialogTR();
        String msg;
        if (str.equals("sure")){
            msg ="Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        }else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin?" ,JOptionPane.YES_NO_OPTION) ==0;
    }
}
