package Dashboard;

import Home.HomeModel;

public class DashboardModel {

    HomeModel homeModel = new HomeModel();
    public int[] ageList(){
        int[] ageList = new int[]{homeModel.getPatientData().size()};
        System.out.println(homeModel.getPatientData().size());
        ageList[0] = 1;

        return ageList;
    }
}
