import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/home/cubit/home_cubit.dart';

class DashboardPage extends StatefulWidget {
  const DashboardPage({super.key});

  @override
  State<DashboardPage> createState() => _DashboardPageState();
}

class _DashboardPageState extends State<DashboardPage> {
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<HomeCubit, HomeState>(
      bloc: getIt<HomeCubit>(),
      builder: (context, state) {
        if (state.user == null && state.status == ScreenStatus.loading) {
          return Center(child: CircularProgressIndicator());
        }

        return Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16.0),
          child: Column(
            children: [
              Row(
                children: [
                  Text(
                    'Welcome, ${state.user?.alias}!\n${state.user?.title}',
                    style: TextStyle(
                      fontSize: 24.0,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              )
            ],
          ),
        );
      },
    );
  }
}
