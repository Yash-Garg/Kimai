import 'package:flutter/material.dart';
import 'package:lucide_icons/lucide_icons.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int currentPageIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: [
          Text('Dashboard'),
          Text('My Times'),
          Text('Calendar'),
        ][currentPageIndex],
        actions: [
          IconButton(
            icon: Icon(LucideIcons.settings2),
            onPressed: () {},
          ),
        ],
      ),
      body: [
        Center(child: Text('My Times')),
        Container(color: Colors.green),
        Center(child: Text('Calendar')),
      ][currentPageIndex],
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (index) => setState(() {
          currentPageIndex = index;
        }),
        selectedIndex: currentPageIndex,
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
  }
}
