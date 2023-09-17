import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/data/models/timesheet_activity.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/home/add_activity.dart';
import 'package:kimai/ui/home/cubit/home_cubit.dart';
import 'package:kimai/utils/extensions.dart';
import 'package:lucide_icons/lucide_icons.dart';

class MyTimesPage extends StatefulWidget {
  const MyTimesPage({super.key});

  @override
  State<MyTimesPage> createState() => _MyTimesPageState();
}

class _MyTimesPageState extends State<MyTimesPage>
    with AutomaticKeepAliveClientMixin {
  @override
  Widget build(BuildContext context) {
    super.build(context);

    return Scaffold(
      body: BlocBuilder<HomeCubit, HomeState>(
        bloc: getIt<HomeCubit>(),
        builder: (_, state) {
          if (state.status == ScreenStatus.success) {
            final sheets = state.sheets;

            return RefreshIndicator.adaptive(
              onRefresh: () => getIt<HomeCubit>().refresh(),
              child: ListView.builder(
                shrinkWrap: true,
                physics: BouncingScrollPhysics(),
                itemCount: sheets.length,
                itemBuilder: (context, index) {
                  final sheet = sheets[index];
                  final project = state.projects
                      .where((customer) => customer.id == sheet.project)
                      .firstOrNull;

                  final projectName = project?.name ?? 'Unknown Customer';
                  final parentName = project?.parentTitle ?? 'Unknown Parent';

                  final activityName = state.activities
                          .where((activity) => activity.id == sheet.activity)
                          .firstOrNull
                          ?.name ??
                      'Unknown Activity';

                  return _TimeSheetCard(
                    activityName: activityName,
                    projectName: projectName,
                    parentName: parentName,
                    sheet: sheet,
                    onTap: () {},
                  );
                },
              ),
            );
          } else {
            return const Center(child: CircularProgressIndicator());
          }
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => context.navigator.push(
          AddActivityPage().route(material: false),
        ),
        child: Icon(LucideIcons.calendarPlus),
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}

class _TimeSheetCard extends StatelessWidget {
  const _TimeSheetCard({
    required this.activityName,
    required this.projectName,
    required this.parentName,
    required this.sheet,
    this.onTap,
  });

  final String activityName;
  final String projectName;
  final String parentName;
  final TimesheetActivity sheet;
  final Function()? onTap;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(
        horizontal: 8.0,
        vertical: 2.0,
      ),
      child: Card(
        child: InkWell(
          borderRadius: BorderRadius.circular(16.0),
          onTap: onTap,
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: ListTile(
              contentPadding: const EdgeInsets.symmetric(
                horizontal: 12.0,
              ),
              enableFeedback: true,
              title: Text('Activity - $activityName'),
              subtitle: Text(
                '$projectName ($parentName)',
                style: TextStyle(fontSize: 16.0),
              ),
              trailing: Text(
                '${Duration(seconds: sheet.duration).inHours} hours',
                style: TextStyle(fontSize: 18.0),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
