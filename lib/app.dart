import 'package:dynamic_color/dynamic_color.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:kimai/ui/landing/landing_page.dart';

class KimaiApp extends StatefulWidget {
  const KimaiApp({super.key});

  @override
  State<KimaiApp> createState() => _KimaiAppState();
}

class _KimaiAppState extends State<KimaiApp> {
  @override
  void initState() {
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.edgeToEdge);
    SystemChrome.setSystemUIOverlayStyle(
      SystemUiOverlayStyle.light.copyWith(
        statusBarColor: Colors.transparent,
        systemNavigationBarColor: Colors.transparent,
      ),
    );
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return DynamicColorBuilder(
      builder: (lightDynamic, darkDynamic) {
        return MaterialApp(
          theme: ThemeData(
            useMaterial3: true,
            colorScheme: lightDynamic,
            brightness: Brightness.light,
            fontFamily: GoogleFonts.rubik().fontFamily,
          ),
          darkTheme: ThemeData(
            useMaterial3: true,
            colorScheme: darkDynamic,
            brightness: Brightness.dark,
            fontFamily: GoogleFonts.rubik().fontFamily,
          ),
          themeMode: ThemeMode.system,
          debugShowCheckedModeBanner: false,
          home: const LandingPage(),
        );
      },
    );
  }
}
