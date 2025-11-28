#!/usr/bin/perl

# use POSIX::strftime::GNU;
use POSIX 'strftime';
use POSIX 'floor';
use POSIX 'ceil';
use List::Util qw[min max];
# use POSIX::strptime qw( strptime );
use Time::Local 'timelocal_nocheck';
use Data::Dump qw(dump);

print "Hello, Macinstruct user!\n";


use constant NUMBER_TYPE   => "NUMBER_TYPE";
use constant STRING_TYPE   => "STRING_TYPE";
use constant DATE_TYPE     => "DATE_TYPE";
use constant NULLABLE      => "NULLABLE";

use constant DATA_TYPE     => 0;
use constant NULL_TYPE     => 1;


our @expectedHeaders     = getExpectedHeaders();

my @expectedDataType = values %{$expectedHeaders[0]};
my @columnHeader     = keys   %{$expectedHeaders[0]};

my ($ww) = values %{$expectedHeaders[0]};

my ($akey) = keys   %{$expectedHeaders[1]};

my %hh = %{$expectedHeaders[0]};
my $ha = $hh{$akey};
# my $ha = $hh{"BOOKING_SOURCE"};
my @hk = keys %hh;

my ($bkey)     = keys   %{$expectedHeaders[0]};
my @bvals      = @{$expectedHeaders[0]{$bkey}};

# my %bhash      = %{$expectedHeaders[0]};
# my @bvals      = @{$bhash{$bkey}};

#my @aval = %{$expectedHeaders[0]}[$akey]


#my @abc = values %expectedHeaders;

my ($total) = scalar @{$expectedDataType[0]};

print "${total}\n";
print "${akey}\n";
print "${ha}\n";
print "aa\n";
print "$hk[0]\n";
print "=========\n";
print "${expectedDataType[0][0]}\n";
print "${expectedDataType[0][1]}\n";
print "a\n";
print "${abc}\n";
print "a\n";
print "${columnHeader[0]}\n";
print "=========\n";
print "${bkey}\n";
print "*********\n";

#my $aa =


my $array = ["1", "a", "3"];

for my $elem (@$array) {   # lexical loop variable
    print "$elem"."\n";
}

my @qq = @{$expectedDataType[0]};

for my $elem (@qq) {   # lexical loop variable
    print "$elem"."\n";
}

print "=========\n";
for my $elem (@bvals) {   # lexical loop variable
    print "$elem"."\n";
}
print "*********\n";
if ($bvals[NULL_TYPE] eq NULLABLE) {
  print "true\n";
} else {
  print "false\n";
}

$record = "aa,,bb";

my @splitRecord = splitComma($record);

for my $elem (@splitRecord) {   # lexical loop variable
    print "$elem"."\n";
    if (defined($elem)) {
        print "defined\n";
    }
}

my ($str);
# $str = 0;
if (defined($str)) {
    print "defined\n";
} else {
    print "not defined\n";
}



sub getExpectedHeaders {
    return (
        { "BOOKING_CODE"              => [STRING_TYPE, NULLABLE]   },
#        { "BOOKING_SOURCE"            => [STRING_TYPE]   }
#        { "BOOKING_CODE"              => STRING_TYPE   },
        { "BOOKING_SOURCE"            => [NUMBER_TYPE]   }
    );
}

sub splitComma {
    my ($string) = @_;
    chomp $string; # Remove trialing newline if it exists
    my @components = split(/,/, $string, -1);
    return @components;
}

sub referenceArray {
    my ($arr) = @_;
    my @record = @{$arr};
    push(@record, "new");

    my ($totalRecords) = scalar @record;
    print "Total records: $totalRecords \n";
    return @record;
}


$text01 = "";
$text02 = "02:00:00";

if ($text01 < $text02) {
  print "text1\n";
} else {
  print "text2\n";
}


print "END!!!\n";


my $date = strftime('%a, %d %b %Y %T %z', localtime);
print "${date}\n";

# my @time = strptime( "2019117", '%Y%j' );
#
# $date = strftime('%a, %d %b %Y %T %z', @time);
# print "${date}\n";


my $time1 = timelocal_nocheck( 0, 0, 0, 117, 0, 2019 );
$date = strftime('%a, %d %b %Y %T %z', localtime($time1));
print "${date}\n";


my @data1;
my $st = "aa";

push(@data1, "aa");
push(@data1, "bb");
push(@data1, "cc");

push(@data3, "aa1");
push(@data3, "bb1");
push(@data3, "cc1");

my ($total1) = scalar @data1;
print "a @data1\n";
print "a @data3\n";
print "a ${total1}\n";

my @data2;
# $data2[0] = \@data1;
# $data2[1] = \@data1;
push(@data2, \@data1);
push(@data2, \@data3);
print "@data2\n";
# $data2[0][0] = "aa";
# $data2[1][1] = "bb";

my ($total2) = scalar @data2;
print "b ${total2}\n";

my @temp = $data2[0];
my ($total3) = scalar @temp;
print "b ${total3}\n";

my $text = $temp[1];

print "c ${text} \n";

dump(@data1);
dump(@data2);

foreach my $line (@data2) {
  dump(@{$line})
}
print "end loop\n";

my $counter = 0;
$counter++;
print "$counter\n";

my $a1;

my $a2 = $a1 // 'default';

print "a $a2 \n";

my $a3 = " hello \n";
my $a4 = $a2 . $a3;

print "$a4";

my @dates;
push(@dates, "20191101");
push(@dates, "20191102");

my $d1 = (substr $dates[0], 6, 2);
my $d2 = (substr $dates[1], 6, 2);

print "d1: $d1 \n";
print "d2: $d2 \n";

my @data3;
push(@data3, "old");
my @data4 = referenceArray(\@data3);
my ($totalRecords) = scalar @data3;
print "Total records: $totalRecords \n";
($totalRecords) = scalar @data4;
print "Total records: $totalRecords \n";


my $str = "050";
my $str2 = $str + 1;
print "Total records: $str2 \n";
my $int1 = sprintf("%03d", $str2);
print "Total records: $int1 \n";

my $calc1 = 7 / 3;
my $calc2 = floor($calc1);
my $calc3 = ceil($calc1);
print "Total records: $calc2 \n";
print "Total records: $calc3 \n";

my $mins = max(100,2);
print "Min: $mins \n";

# my $data4 = pop(@data2);
# dump($data4);
#
# my @data42 = @{pop(@data2)};
# dump(@data42);
#
# my @data5 =  @{$data4};
# dump(@data5);
#
# my $temp = @data5[1];
# # dump($);
# print "b ${temp}\n";

our @alphanumeric = ('a'..'z', 'A'..'Z', 0..9);
our ($alphanumericLength) = scalar @alphanumeric;

sub generate_random_password
{
    my $passwordsize = shift;
    # my @alphanumeric = ('a'..'z', 'A'..'Z', 0..9);
    my $randpassword = join '',
           map $alphanumeric[getRandomCst()], 1..$passwordsize;
   # my $randpassword = join '',
   #        map $alphanumeric[rand $alphanumericLength], 0..$passwordsize;

    $randT = rand @alphanumeric;
    print "RandomT: $randT \n";
    my ($total1) = scalar @alphanumeric;
    print "RandomT: $total1 \n";
    my $a = rand;
    my $b = rand() * $total1;
    print "RandomTb: $b \n";

    return $randpassword;
}

sub getRandomCst {
  my $a = rand;
  return $a * $alphanumericLength;
}

my $returnvalue = generate_random_password(100);
print "Random: $returnvalue \n";
my $rr = rand 10;
print "Random: $rr \n";
my $rrr = 0.267991678490994470546956319833873159561;
print "Random: $rrr \n";

our $inputFile = "hello_request_world";
our $outputFile = $inputFile =~ s/request/response/r;
print "Input: $inputFile \n";
print "Output: $outputFile \n";
