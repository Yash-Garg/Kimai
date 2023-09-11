import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

extension BuildContextExtensions on BuildContext {
  ThemeData get theme => Theme.of(this);

  TextTheme get textTheme => theme.textTheme;

  ColorScheme get colorScheme => theme.colorScheme;

  MediaQueryData get mediaQuery => MediaQuery.of(this);

  NavigatorState get navigator => Navigator.of(this);

  FocusScopeNode get focusScope => FocusScope.of(this);

  ScaffoldState get scaffold => Scaffold.of(this);

  ScaffoldMessengerState get scaffoldMessenger => ScaffoldMessenger.of(this);

  ModalRoute<dynamic>? get modalRoute => ModalRoute.of(this);
}

extension WidgetExtensions on Widget {
  Route<dynamic> get _cRoute => CupertinoPageRoute<dynamic>(
        builder: (_) => this,
      );

  Route<dynamic> get _mRoute => MaterialPageRoute<dynamic>(
        builder: (_) => this,
      );

  Route<dynamic> route({bool material = true}) => material ? _mRoute : _cRoute;
}
