package todriver.sendrequest.maps.view;

import android.app.Activity;


public class OnMapClick {


    public boolean onClick(Activity activity, boolean request){


        if (request){


            return false;

        }



        if (SheetEditTop.isShow()){

            return false;
        }


        if (Btn_send_request_driver.isShow()){

            return false;
        }



        if (Sheet_banzen.isShow()){

            return false;
        }


        if (Sheet_sat7a.isShow()){


            return false;
        }


        if (Sheet_Bettrey.isShow()){

            return false;
        }


        if (Sheet_winsh.isShow()){


            return false;
        }



        if (Sheet_bancher.isShow()){


            return false;
        }



        // 1 -------------------
        if (Btn_Show_Service.isHide() && Sheet_All_Service.isShow()){

            return false;
        }

        if (Btn_Show_Service.isHide()){
            return false;
        }


        // 2 --------------------

        return false;
    }
}
