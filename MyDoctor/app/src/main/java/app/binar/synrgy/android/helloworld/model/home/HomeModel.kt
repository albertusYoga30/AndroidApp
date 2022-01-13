package app.binar.synrgy.android.helloworld.model.home

data class HomeModel(
    var profile: ProfileModel,
    var consultationList:List<ConsultationModel> = emptyList(),
    var topRatedDoctorsList:List<TopRatedDoctorModel> = emptyList(),
    var goodNewsList:List<GoodNewsModel> = emptyList(),
    var profileMenuList:List<ProfileMenuModel> = emptyList()

)