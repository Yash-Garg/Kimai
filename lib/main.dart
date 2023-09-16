import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/app.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/utils/bloc_observer.dart';

Future<void> main() async {
  await initializeApp();
  runApp(KimaiApp());
}

Future<void> initializeApp() async {
  WidgetsFlutterBinding.ensureInitialized();
  await configureDependencies();
  Bloc.observer = AppBlocObserver();
}
