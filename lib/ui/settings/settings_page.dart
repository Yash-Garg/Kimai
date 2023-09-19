import 'package:flutter/material.dart';

class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        physics: BouncingScrollPhysics(),
        slivers: const [
          SliverAppBar.medium(
            title: Text('Settings'),
            titleSpacing: 0,
          ),
          SliverFillRemaining(
            hasScrollBody: false,
            child: Center(child: Text('Settings')),
          )
        ],
      ),
    );
  }
}
