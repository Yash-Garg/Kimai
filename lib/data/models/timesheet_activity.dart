import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:kimai/data/models/models.dart';

part '../../generated/data/models/timesheet_activity.freezed.dart';
part '../../generated/data/models/timesheet_activity.g.dart';

@freezed
class TimesheetActivity with _$TimesheetActivity {
  const factory TimesheetActivity({
    required int activity,
    required int project,
    required List<String> tags,
    required int id,
    required String begin,
    required String end,
    required int duration,
    String? description,
    required double rate,
    required double internalRate,
    required bool exported,
    required bool billable,
    required List<MetaField> metaFields,
  }) = _TimesheetActivity;

  factory TimesheetActivity.fromJson(Map<String, dynamic> json) =>
      _$TimesheetActivityFromJson(json);
}
