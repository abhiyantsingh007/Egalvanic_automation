@echo off
echo Opening Extent Reports...
echo.

if exist "test-output\reports\SampleHierarchicalReport.html" (
    echo Opening Sample Hierarchical Report...
    start "" "test-output\reports\SampleHierarchicalReport.html"
    echo Sample Hierarchical Report opened.
) else (
    echo Sample Hierarchical Report not found.
)

if exist "test-output\reports\AutomationReport.html" (
    echo Opening Main Automation Report...
    start "" "test-output\reports\AutomationReport.html"
    echo Main Automation Report opened.
) else (
    echo Main Automation Report not found.
)

echo.
echo Reports directory contents:
dir "test-output\reports\*.html"
echo.
pause