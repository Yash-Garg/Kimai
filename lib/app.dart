import 'package:dynamic_color/dynamic_color.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/common/bloc/authentication_bloc.dart';
import 'package:kimai/ui/common/bloc/authentication_state.dart';
import 'package:kimai/ui/home/home_page.dart';
import 'package:kimai/ui/landing/landing_page.dart';
import 'package:kimai/utils/extensions.dart';

class KimaiApp extends StatefulWidget {
  const KimaiApp({super.key});

  @override
  State<KimaiApp> createState() => _KimaiAppState();
}

class _KimaiAppState extends State<KimaiApp> {
  @override
  void initState() {
    super.initState();
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

    WidgetsBinding.instance.addPostFrameCallback((_) {
      getIt<AuthenticationBloc>().add(AuthInitialized());
    });
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
          home: Scaffold(
            body: BlocListener<AuthenticationBloc, AuthenticationState>(
              bloc: getIt<AuthenticationBloc>(),
              listenWhen: (pre, curr) => true,
              listener: (context, state) => state.maybeWhen(
                unauthenticated: () => context.navigator.pushReplacement(
                  const LandingPage().route(),
                ),
                authenticated: (user) => context.navigator.pushReplacement(
                  const HomePage().route(),
                ),
                orElse: () => null,
              ),
              child: Center(child: const CircularProgressIndicator()),
            ),
          ),
        );
      },
    );
  }
}
