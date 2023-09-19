import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:intl/intl.dart';
import 'package:kimai/data/models/models.dart';
import 'package:kimai/data/models/screen_status.dart';
import 'package:kimai/di/injectable.dart';
import 'package:kimai/ui/common/custom_textfield.dart';
import 'package:kimai/ui/home/cubit/home_cubit.dart';
import 'package:kimai/utils/extensions.dart';
import 'package:lucide_icons/lucide_icons.dart';

import '../common/dropdown.dart' as dd;
import 'cubit/activity_cubit.dart';

class AddActivityPage extends StatefulWidget {
  final List<Activity> activities;
  final List<Activity> projects;
  final List<Customer> customers;

  const AddActivityPage({
    super.key,
    required this.activities,
    required this.projects,
    required this.customers,
  });

  @override
  State<AddActivityPage> createState() => _AddActivityPageState();
}

class _AddActivityPageState extends State<AddActivityPage> {
  final _formKey = GlobalKey<FormState>();
  late TextEditingController _dateController;
  late TextEditingController _startTimeController;

  @override
  void initState() {
    super.initState();
    _dateController = TextEditingController();
    _startTimeController = TextEditingController();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      getIt<ActivityCubit>().initialize(
        activities: widget.activities,
        projects: widget.projects,
        customers: widget.customers,
      );
    });
  }

  @override
  void dispose() {
    _dateController.dispose();
    _startTimeController.dispose();
    getIt.resetLazySingleton<ActivityCubit>();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<ActivityCubit, ActivityState>(
      bloc: getIt<ActivityCubit>(),
      listenWhen: (pre, curr) => pre.status != curr.status,
      listener: (context, state) {
        if (state.status == ScreenStatus.failed) {
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text(state.error ?? 'Something went wrong!'),
              backgroundColor: Colors.redAccent,
            ),
          );
        }

        if (state.status == ScreenStatus.success) {
          Fluttertoast.showToast(msg: 'Activity created successfully!');
          getIt<HomeCubit>().refresh();
          context.navigator.pop();
        }
      },
      builder: (context, state) {
        _dateController.text = DateFormat('MMMM dd, yyyy').format(
          state.date,
        );
        _startTimeController.text = state.startTime.format(context);

        return Scaffold(
          body: CustomScrollView(
            physics: BouncingScrollPhysics(),
            slivers: [
              SliverAppBar.medium(
                titleSpacing: 0,
                title: Text('Create timesheet'),
              ),
              SliverFillRemaining(
                hasScrollBody: false,
                child: Padding(
                  padding: const EdgeInsets.symmetric(
                    horizontal: 20.0,
                    vertical: 12.0,
                  ),
                  child: Form(
                    key: _formKey,
                    child: Column(
                      children: [
                        CustomTextField(
                          controller: _dateController,
                          hintText: 'May 17, 2023',
                          labelText: 'Date',
                          isReadOnly: true,
                          suffixIcon: IconButton(
                            icon: Icon(LucideIcons.calendarClock),
                            onPressed: () async {
                              final dt = await showDatePicker(
                                    context: context,
                                    initialDate: DateTime.now(),
                                    firstDate: DateTime.now().subtract(
                                      Duration(days: 365),
                                    ),
                                    lastDate: DateTime.now(),
                                  ) ??
                                  DateTime.now();

                              getIt<ActivityCubit>().setDate(dt);
                            },
                          ),
                          onChanged: (val) {},
                          validator: (val) {},
                        ),
                        const SizedBox(height: 24),
                        CustomTextField(
                          controller: _startTimeController,
                          hintText: '11:30',
                          labelText: 'Start Time',
                          isReadOnly: true,
                          suffixIcon: IconButton(
                            icon: Icon(LucideIcons.clock),
                            onPressed: () async {
                              final time = await showTimePicker(
                                    context: context,
                                    initialTime: TimeOfDay.now(),
                                  ) ??
                                  TimeOfDay.now();

                              getIt<ActivityCubit>().setStartTime(time);
                            },
                          ),
                          validator: (val) {},
                        ),
                        const SizedBox(height: 24),
                        dd.DropdownMenu(
                          width: context.mediaQuery.size.width - 40,
                          menuHeight: 300,
                          enableSearch: false,
                          label: Text('Duration (in hours)'),
                          textStyle: TextStyle(fontSize: 16),
                          initialSelection: state.duration,
                          dropdownMenuEntries: state.durationEntries,
                          onSelected: (val) =>
                              getIt<ActivityCubit>().setDuration(val!),
                        ),
                        const SizedBox(height: 24),
                        dd.DropdownMenu(
                          width: context.mediaQuery.size.width - 40,
                          menuHeight: 300,
                          enableSearch: false,
                          label: Text('Customer'),
                          textStyle: TextStyle(fontSize: 16),
                          dropdownMenuEntries: state.customerEntries,
                          onSelected: (val) =>
                              getIt<ActivityCubit>().setCustomer(val!),
                        ),
                        const SizedBox(height: 24),
                        dd.DropdownMenu(
                          width: context.mediaQuery.size.width - 40,
                          menuHeight: 300,
                          enableSearch: false,
                          label: Text('Project'),
                          textStyle: TextStyle(fontSize: 16),
                          dropdownMenuEntries: state.projectEntries,
                          onSelected: (val) =>
                              getIt<ActivityCubit>().setProject(val!),
                        ),
                        const SizedBox(height: 24),
                        dd.DropdownMenu(
                          width: context.mediaQuery.size.width - 40,
                          menuHeight: 300,
                          enableSearch: false,
                          label: Text('Activity'),
                          textStyle: TextStyle(fontSize: 16),
                          dropdownMenuEntries: state.activityEntries,
                          onSelected: (val) =>
                              getIt<ActivityCubit>().setActivity(val!),
                        ),
                        const SizedBox(height: 24),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ),
          floatingActionButton: FloatingActionButton.extended(
            onPressed: () => getIt<ActivityCubit>().save(),
            label: state.status == ScreenStatus.loading
                ? SizedBox(
                    height: 20,
                    width: 20,
                    child: CircularProgressIndicator(),
                  )
                : Text('Create', style: TextStyle(fontSize: 16)),
            icon: state.status == ScreenStatus.loading
                ? null
                : Icon(LucideIcons.upload),
          ),
        );
      },
    );
  }
}
