import 'package:flutter/material.dart';

class WideFab extends StatelessWidget {
  final String label;
  final Function() onPressed;
  final bool isLoading;

  const WideFab({
    super.key,
    required this.label,
    required this.onPressed,
    this.isLoading = false,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(horizontal: 20.0),
      width: MediaQuery.of(context).size.width,
      height: 50,
      child: OutlinedButton(
        onPressed: isLoading ? null : onPressed,
        child: isLoading
            ? const SizedBox(
                height: 20.0,
                width: 20.0,
                child: Center(
                  child: CircularProgressIndicator(strokeWidth: 2.0),
                ),
              )
            : Text(
                label,
                style: TextStyle(
                  fontSize: 18,
                  letterSpacing: 1,
                  fontWeight: FontWeight.bold,
                ),
              ),
      ),
    );
  }
}
