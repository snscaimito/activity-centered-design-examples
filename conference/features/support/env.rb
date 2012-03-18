require "rubygems"
require "json"
require "watir-webdriver"

BASE_URL = "http://localhost:8080/conference"

FILE_TICKETS_PURCHASED = "#{ENV['HOME']}/tickets_purchased.json"
FILE_TICKETS_DEFINED = "#{ENV['HOME']}/tickets.json"

browser = Watir::Browser.new

FileUtils.rm_rf "target/screenshots"

Before do
  FileUtils.rm FILE_TICKETS_DEFINED unless File.exists? FILE_TICKETS_DEFINED
  FileUtils.rm FILE_TICKETS_PURCHASED unless File.exists? FILE_TICKETS_PURCHASED
  @browser = browser
end

After do |scenario|
  if scenario.failed?
    Dir::mkdir('target/screenshots') if not File.directory?('target/screenshots')
    screenshot = "target/screenshots/FAILED_#{scenario.name.gsub(' ','_').gsub(/[^0-9A-Za-z_]/, '')}.png"
    browser.driver.save_screenshot(screenshot)
    embed screenshot, 'image/png'
  end
end

at_exit do
  browser.close
end
