import 'package:flutter/material.dart';
import 'package:kimai/ui/connect/connection_page.dart';
import 'package:kimai/utils/assets.dart';
import 'package:kimai/utils/extensions.dart';

class LandingPage extends StatelessWidget {
  const LandingPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset(
              Assets.kimaiLogo,
              height: 80,
            ),
            const SizedBox(height: 16),
            const Text(
              'Kimai',
              style: TextStyle(
                fontSize: 48,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 4),
            const Text(
              'Time tracking for freelancers',
              style: TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.w400,
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FilledButton.tonal(
        onPressed: () => context.navigator.push(
          ConnectionPage().route(material: false),
        ),
        child: Text(
          'Get started',
          style: TextStyle(fontSize: 16),
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
    );
  }
}
