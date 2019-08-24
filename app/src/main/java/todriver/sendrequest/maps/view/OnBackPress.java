package todriver.sendrequest.maps.view;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import todriver.sendrequest.D;
import todriver.sendrequest.maps.Database;

public class OnBackPress {

    private Btn_Show_Service btn_show_service;
    private Sheet_All_Service sheet_all_service;
    private  Database database;
    private String user_id_request;

    public OnBackPress(Btn_Show_Service btn_show_service, Sheet_All_Service sheet_all_service, Database database, String user_id_request) {
        this.btn_show_service = btn_show_service;
        this.sheet_all_service = sheet_all_service;
        this.database = database;
        this.user_id_request = user_id_request;
    }


    public boolean backPressState(Activity activity, boolean request, boolean service){


        Log.d("STATE",request+"==" +request+"service ==" + service +"");


        if (service){


            if (BottomSheetService.isShow()){


                D.hide_all_sheet();


                BottomSheetService.hide();
                ToolBarSheet.show();


                return false;
            }



            if (ToolBarSheet.isHide()){


                D.hide_all_sheet();

                ToolBarSheet.show();


                return false;

            }




            return true;
        }




        if (request){



            if (RequestClass.isShow()){

                database.CancelRequest(user_id_request);

                D.hide_all_sheet();


                SheetEditTop.show();
                Btn_send_request_driver.show();



                return false;
            }



            if (RequestClass.isHide()){

                database.CancelRequest(user_id_request);

                D.hide_all_sheet();


                SheetEditTop.show();
                Btn_send_request_driver.show();



                return false;
            }




            return true;

        }





        if (SheetEditTop.isShow() && Btn_send_request_driver.isShow()){


            D.hide_all_sheet();

            Sheet_All_Service.show();



            return false;
        }

        if (SheetEditTop.isShow() && Btn_Send_request_To_Company.isShow()){


            D.hide_all_sheet();

            Sheet_All_Service.show();



            return false;
        }


        if (SheetEditTop.isShow()){


            D.hide_all_sheet();

            Sheet_All_Service.show();


            return false;
        }



        if (Btn_Send_request_To_Company.isShow()){


            D.hide_all_sheet();

            Sheet_All_Service.show();


            return false;
        }


        if (Btn_send_request_driver.isShow()){


            D.hide_all_sheet();

            Sheet_All_Service.show();


            return false;
        }






        if (Sheet_banzen.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }


        if (Sheet_sat7a.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }


        if (Sheet_Bettrey.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }


        if (Sheet_winsh.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }



        if (Sheet_bancher.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }




       if (Sheet_key.isShow()){

           D.hide_all_sheet();

        Sheet_All_Service.show();

           return false;
        }




       if (Sheet_Taxi.isShow()){

         D.hide_all_sheet();

        Sheet_All_Service.show();

           return false;
        }




        if (Sheet_Trasnport_Btween_city.isShow()){

            D.hide_all_sheet();

            Sheet_All_Service.show();

            return false;
        }



        if (Sheet_sat7a_3ady_details.isShow()){

            D.hide_all_sheet();

            Sheet_sat7a.show();


            return false;
        }



        if (Sheet_sat7a_hedrolek_details.isShow()){

            D.hide_all_sheet();

            Sheet_sat7a.show();


            return false;
        }







        // 1 -------------------
        if (Btn_Show_Service.isHide() && Sheet_All_Service.isShow()){
            Sheet_All_Service.hide();
            Btn_Show_Service.show();
            ToolBarSheet.show();
            return false;
        }

        if (Btn_Show_Service.isHide()){
            Btn_Show_Service.show();
            ToolBarSheet.show();
            return false;
        }



        // 2 --------------------

        return true;
    }
}
