import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/home/cubit/home_cubit.dart';
import 'package:kimai/ui/home/tabs/dash_page.dart';
import 'package:kimai/ui/home/tabs/times_page.dart';
import 'package:kimai/ui/settings/settings_page.dart';
import 'package:kimai/utils/extensions.dart';
import 'package:lucide_icons/lucide_icons.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage>
    with AutomaticKeepAliveClientMixin {
  late PageController _pageController;

  @override
  void initState() {
    _pageController = PageController(initialPage: 0);
    getIt<HomeCubit>().initialize();
    super.initState();
  }

  @override
  void dispose() {
    _pageController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);

    return BlocBuilder<HomeCubit, HomeState>(
      bloc: getIt<HomeCubit>(),
      builder: (context, state) {
        final selIndex = state.currentPageIndex;

        return Scaffold(
          body: NestedScrollView(
            physics: BouncingScrollPhysics(),
            headerSliverBuilder: (context, innerBoxIsScrolled) {
              return [
                SliverOverlapAbsorber(
                  handle:
                      NestedScrollView.sliverOverlapAbsorberHandleFor(context),
                  sliver: SliverSafeArea(
                    top: false,
                    sliver: SliverAppBar.medium(
                      title: [
                        Text('Dashboard'),
                        Text('My Times'),
                        Text('Calendar'),
                      ][selIndex],
                      actions: [
                        IconButton(
                          icon: Icon(LucideIcons.settings2),
                          onPressed: () => context.navigator.push(
                            SettingsPage().route(material: false),
                          ),
                        ),
                      ],
                    ),
                  ),
                )
              ];
            },
            body: MediaQuery.removePadding(
              removeTop: true,
              removeBottom: true,
              context: context,
              child: PageView(
                controller: _pageController,
                physics: NeverScrollableScrollPhysics(),
                onPageChanged: (index) => getIt<HomeCubit>().changePage(index),
                children: const [
                  DashboardPage(),
                  MyTimesPage(),
                  Center(child: Text('Calendar')),
                ],
              ),
            ),
          ),
          bottomNavigationBar: NavigationBar(
            onDestinationSelected: (index) => _pageController.jumpToPage(index),
            selectedIndex: selIndex,
            destinations: const <Widget>[
              NavigationDestination(
                selectedIcon: Icon(Icons.timelapse),
                icon: Icon(Icons.timelapse_outlined),
                label: 'Dashboard',
              ),
              NavigationDestination(
                selectedIcon: Icon(Icons.view_week),
                icon: Icon(Icons.view_week_outlined),
                label: 'My Times',
              ),
              NavigationDestination(
                selectedIcon: Icon(Icons.calendar_month),
                icon: Icon(Icons.calendar_month_outlined),
                label: 'Calendar',
              ),
            ],
          ),
        );
      },
    );
  }

  @override
  bool get wantKeepAlive => true;
}
