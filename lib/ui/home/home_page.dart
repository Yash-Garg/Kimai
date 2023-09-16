import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/home/cubit/home_cubit.dart';
import 'package:kimai/ui/home/tabs/times_page.dart';
import 'package:lucide_icons/lucide_icons.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<HomeCubit, HomeState>(
      bloc: getIt<HomeCubit>(),
      builder: (context, state) {
        final selIndex = state.currentPageIndex;

        return Scaffold(
          appBar: AppBar(
            title: [
              Text('Dashboard'),
              Text('My Times'),
              Text('Calendar'),
            ][selIndex],
            actions: [
              IconButton(
                icon: Icon(LucideIcons.settings2),
                onPressed: () {},
              ),
            ],
          ),
          body: [
            Center(child: Text('My Times')),
            MyTimesPage(),
            Center(child: Text('Calendar')),
          ][selIndex],
          bottomNavigationBar: NavigationBar(
            onDestinationSelected: (index) => getIt<HomeCubit>().changePage(
              index,
            ),
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
}
