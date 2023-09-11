import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
      SystemUiOverlayStyle.dark.copyWith(
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
    return MaterialApp(
      theme: ThemeData.dark(useMaterial3: true),
      debugShowCheckedModeBanner: false,
      home: const Scaffold(
        body: Center(
          child: Text('KIMAI'),
        ),
      ),
    );
  }
}
