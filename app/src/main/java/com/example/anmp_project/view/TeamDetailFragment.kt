//package com.example.anmp_project.view
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.anmp_project.R
//import com.example.anmp_project.databinding.FragmentTeamDetailBinding
//import com.example.anmp_project.model.TeamDetail
//import com.squareup.picasso.Picasso
//
//class TeamDetailFragment : Fragment() {
//
//    private lateinit var binding: FragmentTeamDetailBinding
//    private lateinit var teamDetailAdapter: TeamDetailAdapter
//    private var teamName: String? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val args = TeamDetailFragmentArgs.fromBundle(requireArguments())
//        teamName = args.teamName
//        val gamePhoto = args.gamePhoto
//
//        binding.txtTeamName.text = teamName
//
//        if (gamePhoto != null) {
//            Picasso.get().load(gamePhoto).into(binding.imageGame)
//        } else {
//            binding.imageGame.setImageResource(R.drawable.baseline_people_24)
//        }
//
//        val teamDetails = fetchTeamDetailsBasedOnTeamName(teamName)
//        teamDetailAdapter = TeamDetailAdapter(teamDetails)
//        binding.teamDetailRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.teamDetailRecyclerView.adapter = teamDetailAdapter
//    }
//
//    private val teamDetailsMap = mapOf(
//        "Valorant Team A" to listOf(
//            TeamDetail("Role 1 Valorant", "Player 1 A", "https://imgix.ranker.com/user_node_img/50113/1002243909/original/1002243909-photo-u1?auto=format&q=60&fit=crop&fm=pjpg&dpr=2&crop=faces&h=150&w=150"),
//            TeamDetail("Role 2 Valorant", "Player 2 A", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/1/18/100T_steel_2020.png/revision/latest?cb=20200904213350"),
//            TeamDetail("Role 3 Valorant", "Player 3 A", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/3/33/100T_Hiko_2020.png/revision/latest?cb=20200808191515"),
//            TeamDetail("Role 4 Valorant", "Player 4 A", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/b/bc/100T_nitr0_2020.png/revision/latest?cb=20200814201935"),
//            TeamDetail("Role 5 Valorant", "Player 5 A", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/0/0b/100T_Pride_2020.png/revision/latest?cb=20200629032846")
//        ),
//        "Valorant Team B" to listOf(
//            TeamDetail("Role 1 Valorant", "Player 1 B", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/8/8f/SEN_dapr_2020.png/revision/latest?cb=20200727130604"),
//            TeamDetail("Role 2 Valorant", "Player 2 B", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/1/16/SEN_ShahZaM_2020.png/revision/latest?cb=20200727130634"),
//            TeamDetail("Role 3 Valorant", "Player 3 B", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/2/2d/SEN_SicK_2020.png/revision/latest?cb=20200727130706"),
//            TeamDetail("Role 4 Valorant", "Player 4 B", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/7/7f/SEN_Sinatraa_2020.png/revision/latest?cb=20200727130747"),
//            TeamDetail("Role 5 Valorant", "Player 5 B", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/7/70/SEN_zombs_2020.png/revision/latest?cb=20200727130816")
//        ),
//        "Valorant Team C" to listOf(
//            TeamDetail("Role 1 Valorant", "Player 1 C", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/c/c6/LAG_2zzy_2020.png/revision/latest?cb=20200803061742"),
//            TeamDetail("Role 2 Valorant", "Player 2 C", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/d/d3/LAG_Flax_2020.png/revision/latest?cb=20200803061700"),
//            TeamDetail("Role 3 Valorant", "Player 3 C", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/a/a1/LAG_KuuKai_2020.png/revision/latest?cb=20200803061813"),
//            TeamDetail("Role 4 Valorant", "Player 4 C", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/9/94/LAG_sakurai_2020.png/revision/latest?cb=20200803061919"),
//            TeamDetail("Role 5 Valorant", "Player 5 C", "https://static.wikia.nocookie.net/valorant_esports_gamepedia_en/images/e/e3/LAG_toru_2020.png/revision/latest?cb=20200803062005")
//            ),
//        "Mobile Legends Team A" to listOf(
//            TeamDetail("Role 1 Mobile Legends", "Player 1 A", "https://liquipedia.net/commons/images/b/be/DEWA_AMYY_MDL_S9.png"),
//            TeamDetail("Role 2 Mobile Legends", "Player 2 A", "https://liquipedia.net/commons/images/1/1d/GEEK_Aboy_S14.png"),
//            TeamDetail("Role 3 Mobile Legends", "Player 3 A", "https://liquipedia.net/commons/images/d/d9/TLID_Aeron_S14.png"),
//            TeamDetail("Role 4 Mobile Legends", "Player 4 A", "https://liquipedia.net/commons/images/e/e6/RBL_Aether_S14.png"),
//            TeamDetail("Role 5 Mobile Legends", "Player 5 A", "https://liquipedia.net/commons/images/d/db/AE_Ahmad_S10.png")
//            ),
//        "Mobile Legends Team B" to listOf(
//            TeamDetail("Role 1 Mobile Legends", "Player 1 B", "https://liquipedia.net/commons/images/4/4f/FNOC_Alberttt_S14.png"),
//            TeamDetail("Role 2 Mobile Legends", "Player 2 B", "https://liquipedia.net/commons/images/8/86/Bossque_Altamiz_MDL_S8.png"),
//            TeamDetail("Role 3 Mobile Legends", "Player 3 B", "https://liquipedia.net/commons/images/e/e0/EVOS_Anavel_S14.png"),
//            TeamDetail("Role 4 Mobile Legends", "Player 4 B", "https://liquipedia.net/commons/images/7/76/GPX_Ando.png"),
//            TeamDetail("Role 5 Mobile Legends", "Player 5 B", "https://liquipedia.net/commons/images/3/3b/Evos_Antimage_S9.png")
//            ),
//        "Mobile Legends Team C" to listOf(
//            TeamDetail("Role 1 Mobile Legends", "Player 1 C", "https://liquipedia.net/commons/images/8/86/TLID_Aran_S14.png"),
//            TeamDetail("Role 2 Mobile Legends", "Player 2 C", "https://liquipedia.net/commons/images/2/29/ARFY_FullBody-MIDDLE.png"),
//            TeamDetail("Role 3 Mobile Legends", "Player 3 C", "https://liquipedia.net/commons/images/b/ba/RBL_Audycs_S14.png"),
//            TeamDetail("Role 4 Mobile Legends", "Player 4 C", "https://liquipedia.net/commons/images/b/b7/Geek_Fam_AyamJAGO_S6.png"),
//            TeamDetail("Role 5 Mobile Legends", "Player 5 C", "https://liquipedia.net/commons/images/1/1e/DEWA_Azuraa_S14.png")
//            ),
//        "League of Legends Team A" to listOf(
//    TeamDetail("Role 1 League of Legends", "Player 1 A", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/7/7f/DRX_Pleata_2024_Split_1_2.png/revision/latest?cb=20240306153600"),
//    TeamDetail("Role 2 League of Legends", "Player 2 A", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/1/15/DRX_Rascal_2024_Split_1_2.png/revision/latest?cb=20240306153602"),
//    TeamDetail("Role 3 League of Legends", "Player 3 A", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/3/31/DRX_Sponge_2024_Split_1_2.png/revision/latest?cb=20240306153558"),
//    TeamDetail("Role 4 League of Legends", "Player 4 A", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/a/a0/DRX_Teddy_2024_Split_1_2.png/revision/latest?cb=20240306153601"),
//    TeamDetail("Role 5 League of Legends", "Player 5 A", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/3/3c/DRX.C_Career_2024_Split_1.png/revision/latest?cb=20240306155845")
//    ),
//        "League of Legends Team B" to listOf(
//    TeamDetail("Role 1 League of Legends", "Player 1 B", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/4/4b/FPX_Care_2024_Split_2.png/revision/latest?cb=20240601023911"),
//    TeamDetail("Role 2 League of Legends", "Player 2 B", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/7/78/FPX_deokdam_2024_Split_2.png/revision/latest?cb=20240601023910"),
//    TeamDetail("Role 3 League of Legends", "Player 3 B", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/c/c3/FPX_Life_2024_Split_2.png/revision/latest?cb=20240601023908"),
//    TeamDetail("Role 4 League of Legends", "Player 4 B", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/c/c1/FPX_TheNiu_2024_Split_2.png/revision/latest?cb=20240601023912"),
//    TeamDetail("Role 5 League of Legends", "Player 5 B", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/d/d2/FPX_Xiaolaohu_2024_Split_2.png/revision/latest?cb=20240601023914")
//    ),
//        "League of Legends Team C" to listOf(
//    TeamDetail("Role 1 League of Legends", "Player 1 C", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/6/63/G2_BrokenBlade_2024_Split_3.png/revision/latest?cb=20241005145656"),
//    TeamDetail("Role 2 League of Legends", "Player 2 C", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/9/90/G2_Caps_2024_Split_3.png/revision/latest?cb=20241005145653"),
//    TeamDetail("Role 3 League of Legends", "Player 3 C", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/2/29/G2_Hans_Sama_2024_Split_3.png/revision/latest?cb=20241005145652"),
//    TeamDetail("Role 4 League of Legends", "Player 4 C", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/a/a7/G2_Mikyx_2024_Split_3.png/revision/latest?cb=20241005145650"),
//    TeamDetail("Role 5 League of Legends", "Player 5 C", "https://static.wikia.nocookie.net/lolesports_gamepedia_en/images/4/4b/G2_Yike_2024_Split_3.png/revision/latest?cb=20241005145655")
//    ),
//        "Call of Duty Team A" to listOf(
//    TeamDetail("Role 1 Call of Duty Team", "Player 1 A", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/9/97/Classic1.png/revision/latest?cb=20170816235749"),
//    TeamDetail("Role 2 Call of Duty Team", "Player 2 A", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/c/c6/Bloodz2.png/revision/latest?cb=20170816232925"),
//    TeamDetail("Role 3 Call of Duty Team", "Player 3 A", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/b/b7/Crimsix2.png/revision/latest?cb=20170817000616"),
//    TeamDetail("Role 4 Call of Duty Team", "Player 4 A", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/b/b0/Crowster2.png/revision/latest?cb=20170817000705"),
//    TeamDetail("Role 5 Call of Duty Team", "Player 5 A", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/7/72/FEARS.png/revision/latest?cb=20170817005607")
//    ),
//        "Call of Duty Team B" to listOf(
//    TeamDetail("Role 1 Call of Duty Team", "Player 1 B", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/a/a0/Flawless.jpg/revision/latest?cb=20170817010657"),
//    TeamDetail("Role 2 Call of Duty Team", "Player 2 B", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/b/b7/Flux.jpeg/revision/latest?cb=20170817010715"),
//    TeamDetail("Role 3 Call of Duty Team", "Player 3 B", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/2/2e/JakeProfile3.jpg/revision/latest?cb=20170817020303"),
//    TeamDetail("Role 4 Call of Duty Team", "Player 4 B", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/a/ae/Hysteria.jpg/revision/latest?cb=20170817013812"),
//    TeamDetail("Role 5 Call of Duty Team", "Player 5 B", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/8/84/Jurd.jpg/revision/latest?cb=20170817020545")
//    ),
//        "Call of Duty Team C" to listOf(
//    TeamDetail("Role 1 Call of Duty Team", "Player 1 C", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/2/24/Lawless.jpeg/revision/latest?cb=20170817021942"),
//    TeamDetail("Role 2 Call of Duty Team", "Player 2 C", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/8/88/Nadeshot.png/revision/latest?cb=20170817025820"),
//    TeamDetail("Role 3 Call of Duty Team", "Player 3 C", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/d/dc/Nifty.jpeg/revision/latest?cb=20170817030306"),
//    TeamDetail("Role 4 Call of Duty Team", "Player 4 C", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/8/8c/Sharp.png/revision/latest?cb=20170817041851"),
//    TeamDetail("Role 5 Call of Duty Team", "Player 5 C", "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/a/ac/Scumpi.jpg/revision/latest?cb=20170817041319")
//    ),
//        "Dota 2 Team A" to listOf(
//            TeamDetail("Role 1 Dota 2", "Player 1 A", "https://static.wikia.nocookie.net/dota2_gamepedia/images/e/eb/Player_image_Algodonchin.jpg/revision/latest?cb=20160831114724"),
//            TeamDetail("Role 2 Dota 2", "Player 2 A", "https://static.wikia.nocookie.net/dota2_gamepedia/images/0/03/Ddx.png/revision/latest?cb=20160831115711"),
//            TeamDetail("Role 3 Dota 2", "Player 3 A", "https://static.wikia.nocookie.net/dota2_gamepedia/images/2/25/Ana.jpg/revision/latest?cb=20210614164913"),
//            TeamDetail("Role 4 Dota 2", "Player 4 A", "https://static.wikia.nocookie.net/dota2_gamepedia/images/3/3d/Kpii.jpg/revision/latest?cb=20210616045736"),
//            TeamDetail("Role 5 Dota 2", "Player 5 A", "https://static.wikia.nocookie.net/dota2_gamepedia/images/0/01/Fng.jpg/revision/latest?cb=20150624104830")
//    ),
//        "Dota 2 Team B" to listOf(
//            TeamDetail("Role 1 Dota 2", "Player 1 B", "https://static.wikia.nocookie.net/dota2_gamepedia/images/0/03/HFn.jpg/revision/latest?cb=20210614133542"),
//            TeamDetail("Role 2 Dota 2", "Player 2 B", "https://static.wikia.nocookie.net/dota2_gamepedia/images/b/b8/Tavo.jpg/revision/latest?cb=20210527092811"),
//            TeamDetail("Role 3 Dota 2", "Player 3 B", "https://static.wikia.nocookie.net/dota2_gamepedia/images/d/d3/Mindcontrol.jpg/revision/latest?cb=20220531171929"),
//            TeamDetail("Role 4 Dota 2", "Player 4 B", "https://static.wikia.nocookie.net/dota2_gamepedia/images/5/5d/1437.jpg/revision/latest?cb=20150627001259"),
//            TeamDetail("Role 5 Dota 2", "Player 5 B", "https://static.wikia.nocookie.net/dota2_gamepedia/images/6/60/Arteezy.jpg/revision/latest?cb=20170930130704")
//            ),
//        "Dota 2 Team C" to listOf(
//            TeamDetail("Role 1 Dota 2", "Player 1 C", "https://static.wikia.nocookie.net/dota2_gamepedia/images/a/a2/Aui_2000.jpg/revision/latest?cb=20150626223450"),
//            TeamDetail("Role 2 Dota 2", "Player 2 C", "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/8c/EternaLEnVy.jpg/revision/latest?cb=20210607094544"),
//            TeamDetail("Role 3 Dota 2", "Player 3 C", "https://static.wikia.nocookie.net/dota2_gamepedia/images/2/2b/Jeyo.jpg/revision/latest?cb=20210616043628"),
//            TeamDetail("Role 4 Dota 2", "Player 4 C", "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/87/MoonMeander_Profile.jpg/revision/latest?cb=20170930125234"),
//            TeamDetail("Role 5 Dota 2", "Player 5 C", "https://static.wikia.nocookie.net/dota2_gamepedia/images/9/97/Paintitgold.png/revision/latest?cb=20121121141811")
//            ),
//        "Fortnite Team A" to listOf(
//            TeamDetail("Role 1 Fortnite", "Player 1 A", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/d/de/ZETA_Zagou.png/revision/latest?cb=20240531202731"),
//            TeamDetail("Role 2 Fortnite", "Player 2 A", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/9/94/SEN_Bugha_2019.png/revision/latest?cb=20220712133932"),
//            TeamDetail("Role 3 Fortnite", "Player 3 A", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/4/4e/GXR_Malibuca.png/revision/latest?cb=20230513082645"),
//            TeamDetail("Role 4 Fortnite", "Player 4 A", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/4/41/Queasy_2022.png/revision/latest?cb=20221229110326")
//            ),
//        "Fortnite Team B" to listOf(
//            TeamDetail("Role 1 Fortnite", "Player 1 B", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/8/81/Monaco_Veno.png/revision/latest?cb=20240511075440"),
//            TeamDetail("Role 2 Fortnite", "Player 2 B", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/5/5b/Falcons_TaySon_2023.png/revision/latest?cb=20230804122836"),
//            TeamDetail("Role 3 Fortnite", "Player 3 B", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/5/5a/PWR_Alex.png/revision/latest?cb=20230813112432"),
//            TeamDetail("Role 4 Fortnite", "Player 4 B", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/b/b5/FaZe_K1nG.png/revision/latest?cb=20230521171606")
//            ),
//        "Fortnite Team C" to listOf(
//            TeamDetail("Role 1 Fortnite", "Player 1 C", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/0/01/Phzin.png/revision/latest?cb=20230820153946"),
//            TeamDetail("Role 2 Fortnite", "Player 2 C", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/5/57/Falcons_Spy_2021.png/revision/latest?cb=20210530184340"),
//            TeamDetail("Role 3 Fortnite", "Player 3 C", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/b/ba/Elite_Eomzo.png/revision/latest?cb=20240531193711"),
//            TeamDetail("Role 4 Fortnite", "Player 4 C", "https://static.wikia.nocookie.net/fortnite_esports_gamepedia_en/images/2/24/Man_Threats_2023.png/revision/latest?cb=20240209140552")
//            ),
//    )
//
//    private fun fetchTeamDetailsBasedOnTeamName(teamName: String?): List<TeamDetail> {
//        return teamDetailsMap[teamName] ?: emptyList()
//
//    }
//}
//
//
