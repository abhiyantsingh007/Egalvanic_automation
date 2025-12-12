import os
from datetime import datetime

# Create the HTML content for a realistic Extent report with our hierarchy
html_content = '''
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ACME Test Automation Report - CHROME</title>
    <style>
        /* Extent Reports CSS */
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 14px;
            line-height: 1.42857143;
            color: #333;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #222;
            border-color: #080808;
            border: 1px solid transparent;
            border-radius: 0;
            margin-bottom: 0;
        }
        .navbar-brand {
            color: #9d9d9d;
            padding: 15px;
            font-size: 18px;
            line-height: 20px;
            height: 50px;
        }
        .container-fluid {
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
        }
        .row {
            margin-right: -15px;
            margin-left: -15px;
        }
        .col-md-12 {
            position: relative;
            min-height: 1px;
            padding-right: 15px;
            padding-left: 15px;
        }
        .card {
            position: relative;
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .card-header {
            padding: 10px 15px;
            border-bottom: 1px solid #e5e5e5;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;
        }
        .card-body {
            padding: 15px;
        }
        .test-name {
            font-weight: bold;
            font-size: 16px;
            margin-bottom: 10px;
        }
        .test-status {
            display: inline-block;
            padding: 3px 7px;
            font-size: 12px;
            font-weight: bold;
            line-height: 1;
            color: #fff;
            text-align: center;
            white-space: nowrap;
            vertical-align: baseline;
            border-radius: 10px;
        }
        .pass {
            background-color: #5cb85c;
        }
        .fail {
            background-color: #d9534f;
        }
        .info {
            background-color: #5bc0de;
        }
        .warning {
            background-color: #f0ad4e;
        }
        .test-steps {
            margin-top: 10px;
            padding-left: 20px;
        }
        .step {
            margin-bottom: 5px;
        }
        .step-status {
            display: inline-block;
            width: 20px;
            text-align: center;
        }
        .hierarchy {
            margin-left: 20px;
            border-left: 2px solid #ddd;
            padding-left: 15px;
        }
        .feature {
            margin-top: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-left: 4px solid #5bc0de;
        }
        .sub-feature {
            margin-top: 15px;
            padding: 8px;
            background-color: #fff;
            border-left: 3px solid #5cb85c;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <span class="navbar-brand">ACME Test Automation Report</span>
            </div>
        </div>
    </nav>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h4>Test Summary</h4>
                    </div>
                    <div class="card-body">
                        <p><strong>Organization:</strong> ACME</p>
                        <p><strong>Environment:</strong> Test</p>
                        <p><strong>Browser:</strong> CHROME</p>
                        <p><strong>Tester:</strong> QA Automation Engineer</p>
                        <p><strong>Timestamp:</strong> ''' + datetime.now().strftime("%Y-%m-%d %H:%M:%S") + '''</p>
                    </div>
                </div>
                
                <!-- Hierarchical Test Structure -->
                <div class="card">
                    <div class="card-header">
                        <h4>Module: Assets</h4>
                    </div>
                    <div class="card-body">
                        <div class="test-status pass">PASS</div>
                        
                        <!-- Feature: List -->
                        <div class="feature">
                            <div class="test-name">Feature: List</div>
                            <div class="test-status pass">PASS</div>
                            
                            <!-- Sub-Feature: CRUD Assets -->
                            <div class="hierarchy">
                                <div class="sub-feature">
                                    <div class="test-name">Sub-Feature: CRUD Assets</div>
                                    <div class="test-status pass">PASS</div>
                                    
                                    <div class="test-steps">
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Starting UI testing for dashboard, create asset and edit asset flow</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Login Successful</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Site Selection Successful</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Assets Page Loaded</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Asset Creation Phase 1 Successful</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Asset Edit Phase 2 Successful</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>UI testing completed successfully</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Full UI flow completed successfully</span>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- Sub-Feature: Search -->
                                <div class="sub-feature">
                                    <div class="test-name">Sub-Feature: Search</div>
                                    <div class="test-status pass">PASS</div>
                                    
                                    <div class="test-steps">
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Search functionality test passed</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Filtering by name works correctly</span>
                                        </div>
                                        <div class="step">
                                            <span class="step-status pass">✓</span>
                                            <span>Advanced search filters functional</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Additional Test Categories -->
                <div class="card">
                    <div class="card-header">
                        <h4>API Testing</h4>
                    </div>
                    <div class="card-body">
                        <div class="test-status pass">PASS</div>
                        <div class="test-steps">
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>Authentication token obtained successfully</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>GET /auth/me endpoint test passed</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>GET /users endpoint test passed</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>POST /users endpoint correctly rejected (as expected)</span>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="card">
                    <div class="card-header">
                        <h4>Performance Testing</h4>
                    </div>
                    <div class="card-body">
                        <div class="test-status pass">PASS</div>
                        <div class="test-steps">
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>Excellent page load time (&lt; 2 seconds)</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>Page load times are consistent (difference &lt; 1 second)</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>All concurrent requests successful (100% success rate)</span>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="card">
                    <div class="card-header">
                        <h4>Security Testing</h4>
                    </div>
                    <div class="card-body">
                        <div class="test-status pass">PASS</div>
                        <div class="test-steps">
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>SQL injection protection is working correctly</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>XSS protection is working correctly</span>
                            </div>
                            <div class="step">
                                <span class="step-status pass">✓</span>
                                <span>Missing authentication protection is working correctly</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
'''

# Create reports directory if it doesn't exist
os.makedirs("test-output/reports", exist_ok=True)

# Write the HTML content to a file
with open("test-output/reports/SampleHierarchicalReport.html", "w", encoding="utf-8") as f:
    f.write(html_content)

print("Sample hierarchical Extent report generated successfully!")
print("Report saved to: test-output/reports/SampleHierarchicalReport.html")