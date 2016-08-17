package com.codetrump.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codetrump.leetcode.util.ArrayUtil;
import com.codetrump.leetcode.util.LeetcodeUtils;

public abstract class WordSearchII {
	public abstract List<String> findWords(char[][] board, String[] words);
	public static void main(String[] args) {
		WordSearchII instance = new SolutionV();
		char[][] board; String[] words;
		List<String> results;
		long t1, t2;
		
		// acdb
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"ab","cd"});
//		words = new String[]{"acdb"};

//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"ab","aa"});
//		words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
		
		// a
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"a"});
//		words = new String[]{"a", "a"};
		
		// cd
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"ab", "cd"});
//		words = new String[]{"cd"};
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"oaan","etae","ihkr","iflv"});
//		words = new String[]{"oath","pea","eat","rain"};
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"oaan","etae","ihkr","iflv"});
//		words = new String[]{"oath","pea","eat","rain"};
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"seenew","tmriva","obsibd","wmysen","ltsnsa","iezlgn"});
//		words = ArrayUtil.str2strArray(LeetcodeUtils.readText(instance));
		
//		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"aaaa","aaaa","aaaa","aaaa","bcde","fghi","jklm","nopq","rstu","vwxy","zzzz"});
//		words = new String[]{"aaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaab","aaaaaaaaaaaaaaac","aaaaaaaaaaaaaaad","aaaaaaaaaaaaaaae","aaaaaaaaaaaaaaaf","aaaaaaaaaaaaaaag","aaaaaaaaaaaaaaah","aaaaaaaaaaaaaaai","aaaaaaaaaaaaaaaj","aaaaaaaaaaaaaaak","aaaaaaaaaaaaaaal","aaaaaaaaaaaaaaam","aaaaaaaaaaaaaaan","aaaaaaaaaaaaaaao","aaaaaaaaaaaaaaap","aaaaaaaaaaaaaaaq","aaaaaaaaaaaaaaar","aaaaaaaaaaaaaaas","aaaaaaaaaaaaaaat","aaaaaaaaaaaaaaau","aaaaaaaaaaaaaaav","aaaaaaaaaaaaaaaw","aaaaaaaaaaaaaaax","aaaaaaaaaaaaaaay","aaaaaaaaaaaaaaaz","aaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaab","aaaaaaaaaaaaaaac","aaaaaaaaaaaaaaad","aaaaaaaaaaaaaaae","aaaaaaaaaaaaaaaf","aaaaaaaaaaaaaaag","aaaaaaaaaaaaaaah","aaaaaaaaaaaaaaai","aaaaaaaaaaaaaaaj","aaaaaaaaaaaaaaak","aaaaaaaaaaaaaaal","aaaaaaaaaaaaaaam","aaaaaaaaaaaaaaan","aaaaaaaaaaaaaaao","aaaaaaaaaaaaaaap","aaaaaaaaaaaaaaaq","aaaaaaaaaaaaaaar","aaaaaaaaaaaaaaas","aaaaaaaaaaaaaaat","aaaaaaaaaaaaaaau","aaaaaaaaaaaaaaav","aaaaaaaaaaaaaaaw","aaaaaaaaaaaaaaax","aaaaaaaaaaaaaaay","aaaaaaaaaaaaaaaz","aaaaaaaaaaaaaaba","aaaaaaaaaaaaaabb","aaaaaaaaaaaaaabc","aaaaaaaaaaaaaabd","aaaaaaaaaaaaaabe","aaaaaaaaaaaaaabf","aaaaaaaaaaaaaabg","aaaaaaaaaaaaaabh","aaaaaaaaaaaaaabi","aaaaaaaaaaaaaabj","aaaaaaaaaaaaaabk","aaaaaaaaaaaaaabl","aaaaaaaaaaaaaabm","aaaaaaaaaaaaaabn","aaaaaaaaaaaaaabo","aaaaaaaaaaaaaabp","aaaaaaaaaaaaaabq","aaaaaaaaaaaaaabr","aaaaaaaaaaaaaabs","aaaaaaaaaaaaaabt","aaaaaaaaaaaaaabu","aaaaaaaaaaaaaabv","aaaaaaaaaaaaaabw","aaaaaaaaaaaaaabx","aaaaaaaaaaaaaaby","aaaaaaaaaaaaaabz","aaaaaaaaaaaaaaca","aaaaaaaaaaaaaacb","aaaaaaaaaaaaaacc","aaaaaaaaaaaaaacd","aaaaaaaaaaaaaace","aaaaaaaaaaaaaacf","aaaaaaaaaaaaaacg","aaaaaaaaaaaaaach","aaaaaaaaaaaaaaci","aaaaaaaaaaaaaacj","aaaaaaaaaaaaaack","aaaaaaaaaaaaaacl","aaaaaaaaaaaaaacm","aaaaaaaaaaaaaacn","aaaaaaaaaaaaaaco","aaaaaaaaaaaaaacp","aaaaaaaaaaaaaacq","aaaaaaaaaaaaaacr","aaaaaaaaaaaaaacs","aaaaaaaaaaaaaact","aaaaaaaaaaaaaacu","aaaaaaaaaaaaaacv","aaaaaaaaaaaaaacw","aaaaaaaaaaaaaacx","aaaaaaaaaaaaaacy","aaaaaaaaaaaaaacz","aaaaaaaaaaaaaada","aaaaaaaaaaaaaadb","aaaaaaaaaaaaaadc","aaaaaaaaaaaaaadd","aaaaaaaaaaaaaade","aaaaaaaaaaaaaadf","aaaaaaaaaaaaaadg","aaaaaaaaaaaaaadh","aaaaaaaaaaaaaadi","aaaaaaaaaaaaaadj","aaaaaaaaaaaaaadk","aaaaaaaaaaaaaadl","aaaaaaaaaaaaaadm","aaaaaaaaaaaaaadn","aaaaaaaaaaaaaado","aaaaaaaaaaaaaadp","aaaaaaaaaaaaaadq","aaaaaaaaaaaaaadr","aaaaaaaaaaaaaads","aaaaaaaaaaaaaadt","aaaaaaaaaaaaaadu","aaaaaaaaaaaaaadv","aaaaaaaaaaaaaadw","aaaaaaaaaaaaaadx","aaaaaaaaaaaaaady","aaaaaaaaaaaaaadz","aaaaaaaaaaaaaaea","aaaaaaaaaaaaaaeb","aaaaaaaaaaaaaaec","aaaaaaaaaaaaaaed","aaaaaaaaaaaaaaee","aaaaaaaaaaaaaaef","aaaaaaaaaaaaaaeg","aaaaaaaaaaaaaaeh","aaaaaaaaaaaaaaei","aaaaaaaaaaaaaaej","aaaaaaaaaaaaaaek","aaaaaaaaaaaaaael","aaaaaaaaaaaaaaem","aaaaaaaaaaaaaaen","aaaaaaaaaaaaaaeo","aaaaaaaaaaaaaaep","aaaaaaaaaaaaaaeq","aaaaaaaaaaaaaaer","aaaaaaaaaaaaaaes","aaaaaaaaaaaaaaet","aaaaaaaaaaaaaaeu","aaaaaaaaaaaaaaev","aaaaaaaaaaaaaaew","aaaaaaaaaaaaaaex","aaaaaaaaaaaaaaey","aaaaaaaaaaaaaaez","aaaaaaaaaaaaaafa","aaaaaaaaaaaaaafb","aaaaaaaaaaaaaafc","aaaaaaaaaaaaaafd","aaaaaaaaaaaaaafe","aaaaaaaaaaaaaaff","aaaaaaaaaaaaaafg","aaaaaaaaaaaaaafh","aaaaaaaaaaaaaafi","aaaaaaaaaaaaaafj","aaaaaaaaaaaaaafk","aaaaaaaaaaaaaafl","aaaaaaaaaaaaaafm","aaaaaaaaaaaaaafn","aaaaaaaaaaaaaafo","aaaaaaaaaaaaaafp","aaaaaaaaaaaaaafq","aaaaaaaaaaaaaafr","aaaaaaaaaaaaaafs","aaaaaaaaaaaaaaft","aaaaaaaaaaaaaafu","aaaaaaaaaaaaaafv","aaaaaaaaaaaaaafw","aaaaaaaaaaaaaafx","aaaaaaaaaaaaaafy","aaaaaaaaaaaaaafz","aaaaaaaaaaaaaaga","aaaaaaaaaaaaaagb","aaaaaaaaaaaaaagc","aaaaaaaaaaaaaagd","aaaaaaaaaaaaaage","aaaaaaaaaaaaaagf","aaaaaaaaaaaaaagg","aaaaaaaaaaaaaagh","aaaaaaaaaaaaaagi","aaaaaaaaaaaaaagj","aaaaaaaaaaaaaagk","aaaaaaaaaaaaaagl","aaaaaaaaaaaaaagm","aaaaaaaaaaaaaagn","aaaaaaaaaaaaaago","aaaaaaaaaaaaaagp","aaaaaaaaaaaaaagq","aaaaaaaaaaaaaagr","aaaaaaaaaaaaaags","aaaaaaaaaaaaaagt","aaaaaaaaaaaaaagu","aaaaaaaaaaaaaagv","aaaaaaaaaaaaaagw","aaaaaaaaaaaaaagx","aaaaaaaaaaaaaagy","aaaaaaaaaaaaaagz","aaaaaaaaaaaaaaha","aaaaaaaaaaaaaahb","aaaaaaaaaaaaaahc","aaaaaaaaaaaaaahd","aaaaaaaaaaaaaahe","aaaaaaaaaaaaaahf","aaaaaaaaaaaaaahg","aaaaaaaaaaaaaahh","aaaaaaaaaaaaaahi","aaaaaaaaaaaaaahj","aaaaaaaaaaaaaahk","aaaaaaaaaaaaaahl","aaaaaaaaaaaaaahm","aaaaaaaaaaaaaahn","aaaaaaaaaaaaaaho","aaaaaaaaaaaaaahp","aaaaaaaaaaaaaahq","aaaaaaaaaaaaaahr","aaaaaaaaaaaaaahs","aaaaaaaaaaaaaaht","aaaaaaaaaaaaaahu","aaaaaaaaaaaaaahv","aaaaaaaaaaaaaahw","aaaaaaaaaaaaaahx","aaaaaaaaaaaaaahy","aaaaaaaaaaaaaahz","aaaaaaaaaaaaaaia","aaaaaaaaaaaaaaib","aaaaaaaaaaaaaaic","aaaaaaaaaaaaaaid","aaaaaaaaaaaaaaie","aaaaaaaaaaaaaaif","aaaaaaaaaaaaaaig","aaaaaaaaaaaaaaih","aaaaaaaaaaaaaaii","aaaaaaaaaaaaaaij","aaaaaaaaaaaaaaik","aaaaaaaaaaaaaail","aaaaaaaaaaaaaaim","aaaaaaaaaaaaaain","aaaaaaaaaaaaaaio","aaaaaaaaaaaaaaip","aaaaaaaaaaaaaaiq","aaaaaaaaaaaaaair","aaaaaaaaaaaaaais","aaaaaaaaaaaaaait","aaaaaaaaaaaaaaiu","aaaaaaaaaaaaaaiv","aaaaaaaaaaaaaaiw","aaaaaaaaaaaaaaix","aaaaaaaaaaaaaaiy","aaaaaaaaaaaaaaiz","aaaaaaaaaaaaaaja","aaaaaaaaaaaaaajb","aaaaaaaaaaaaaajc","aaaaaaaaaaaaaajd","aaaaaaaaaaaaaaje","aaaaaaaaaaaaaajf","aaaaaaaaaaaaaajg","aaaaaaaaaaaaaajh","aaaaaaaaaaaaaaji","aaaaaaaaaaaaaajj","aaaaaaaaaaaaaajk","aaaaaaaaaaaaaajl","aaaaaaaaaaaaaajm","aaaaaaaaaaaaaajn","aaaaaaaaaaaaaajo","aaaaaaaaaaaaaajp","aaaaaaaaaaaaaajq","aaaaaaaaaaaaaajr","aaaaaaaaaaaaaajs","aaaaaaaaaaaaaajt","aaaaaaaaaaaaaaju","aaaaaaaaaaaaaajv","aaaaaaaaaaaaaajw","aaaaaaaaaaaaaajx","aaaaaaaaaaaaaajy","aaaaaaaaaaaaaajz","aaaaaaaaaaaaaaka","aaaaaaaaaaaaaakb","aaaaaaaaaaaaaakc","aaaaaaaaaaaaaakd","aaaaaaaaaaaaaake","aaaaaaaaaaaaaakf","aaaaaaaaaaaaaakg","aaaaaaaaaaaaaakh","aaaaaaaaaaaaaaki","aaaaaaaaaaaaaakj","aaaaaaaaaaaaaakk","aaaaaaaaaaaaaakl","aaaaaaaaaaaaaakm","aaaaaaaaaaaaaakn","aaaaaaaaaaaaaako","aaaaaaaaaaaaaakp","aaaaaaaaaaaaaakq","aaaaaaaaaaaaaakr","aaaaaaaaaaaaaaks","aaaaaaaaaaaaaakt","aaaaaaaaaaaaaaku","aaaaaaaaaaaaaakv","aaaaaaaaaaaaaakw","aaaaaaaaaaaaaakx","aaaaaaaaaaaaaaky","aaaaaaaaaaaaaakz","aaaaaaaaaaaaaala","aaaaaaaaaaaaaalb","aaaaaaaaaaaaaalc","aaaaaaaaaaaaaald","aaaaaaaaaaaaaale","aaaaaaaaaaaaaalf","aaaaaaaaaaaaaalg","aaaaaaaaaaaaaalh","aaaaaaaaaaaaaali","aaaaaaaaaaaaaalj","aaaaaaaaaaaaaalk","aaaaaaaaaaaaaall","aaaaaaaaaaaaaalm","aaaaaaaaaaaaaaln","aaaaaaaaaaaaaalo","aaaaaaaaaaaaaalp","aaaaaaaaaaaaaalq","aaaaaaaaaaaaaalr","aaaaaaaaaaaaaals","aaaaaaaaaaaaaalt","aaaaaaaaaaaaaalu","aaaaaaaaaaaaaalv","aaaaaaaaaaaaaalw","aaaaaaaaaaaaaalx","aaaaaaaaaaaaaaly","aaaaaaaaaaaaaalz","aaaaaaaaaaaaaama","aaaaaaaaaaaaaamb","aaaaaaaaaaaaaamc","aaaaaaaaaaaaaamd","aaaaaaaaaaaaaame","aaaaaaaaaaaaaamf","aaaaaaaaaaaaaamg","aaaaaaaaaaaaaamh","aaaaaaaaaaaaaami","aaaaaaaaaaaaaamj","aaaaaaaaaaaaaamk","aaaaaaaaaaaaaaml","aaaaaaaaaaaaaamm","aaaaaaaaaaaaaamn","aaaaaaaaaaaaaamo","aaaaaaaaaaaaaamp","aaaaaaaaaaaaaamq","aaaaaaaaaaaaaamr","aaaaaaaaaaaaaams","aaaaaaaaaaaaaamt","aaaaaaaaaaaaaamu","aaaaaaaaaaaaaamv","aaaaaaaaaaaaaamw","aaaaaaaaaaaaaamx","aaaaaaaaaaaaaamy","aaaaaaaaaaaaaamz","aaaaaaaaaaaaaana","aaaaaaaaaaaaaanb","aaaaaaaaaaaaaanc","aaaaaaaaaaaaaand","aaaaaaaaaaaaaane","aaaaaaaaaaaaaanf","aaaaaaaaaaaaaang","aaaaaaaaaaaaaanh","aaaaaaaaaaaaaani","aaaaaaaaaaaaaanj","aaaaaaaaaaaaaank","aaaaaaaaaaaaaanl","aaaaaaaaaaaaaanm","aaaaaaaaaaaaaann","aaaaaaaaaaaaaano","aaaaaaaaaaaaaanp","aaaaaaaaaaaaaanq","aaaaaaaaaaaaaanr","aaaaaaaaaaaaaans","aaaaaaaaaaaaaant","aaaaaaaaaaaaaanu","aaaaaaaaaaaaaanv","aaaaaaaaaaaaaanw","aaaaaaaaaaaaaanx","aaaaaaaaaaaaaany","aaaaaaaaaaaaaanz","aaaaaaaaaaaaaaoa","aaaaaaaaaaaaaaob","aaaaaaaaaaaaaaoc","aaaaaaaaaaaaaaod","aaaaaaaaaaaaaaoe","aaaaaaaaaaaaaaof","aaaaaaaaaaaaaaog","aaaaaaaaaaaaaaoh","aaaaaaaaaaaaaaoi","aaaaaaaaaaaaaaoj","aaaaaaaaaaaaaaok","aaaaaaaaaaaaaaol","aaaaaaaaaaaaaaom","aaaaaaaaaaaaaaon","aaaaaaaaaaaaaaoo","aaaaaaaaaaaaaaop","aaaaaaaaaaaaaaoq","aaaaaaaaaaaaaaor","aaaaaaaaaaaaaaos","aaaaaaaaaaaaaaot","aaaaaaaaaaaaaaou","aaaaaaaaaaaaaaov","aaaaaaaaaaaaaaow","aaaaaaaaaaaaaaox","aaaaaaaaaaaaaaoy","aaaaaaaaaaaaaaoz","aaaaaaaaaaaaaapa","aaaaaaaaaaaaaapb","aaaaaaaaaaaaaapc","aaaaaaaaaaaaaapd","aaaaaaaaaaaaaape","aaaaaaaaaaaaaapf","aaaaaaaaaaaaaapg","aaaaaaaaaaaaaaph","aaaaaaaaaaaaaapi","aaaaaaaaaaaaaapj","aaaaaaaaaaaaaapk","aaaaaaaaaaaaaapl","aaaaaaaaaaaaaapm","aaaaaaaaaaaaaapn","aaaaaaaaaaaaaapo","aaaaaaaaaaaaaapp","aaaaaaaaaaaaaapq","aaaaaaaaaaaaaapr","aaaaaaaaaaaaaaps","aaaaaaaaaaaaaapt","aaaaaaaaaaaaaapu","aaaaaaaaaaaaaapv","aaaaaaaaaaaaaapw","aaaaaaaaaaaaaapx","aaaaaaaaaaaaaapy","aaaaaaaaaaaaaapz","aaaaaaaaaaaaaaqa","aaaaaaaaaaaaaaqb","aaaaaaaaaaaaaaqc","aaaaaaaaaaaaaaqd","aaaaaaaaaaaaaaqe","aaaaaaaaaaaaaaqf","aaaaaaaaaaaaaaqg","aaaaaaaaaaaaaaqh","aaaaaaaaaaaaaaqi","aaaaaaaaaaaaaaqj","aaaaaaaaaaaaaaqk","aaaaaaaaaaaaaaql","aaaaaaaaaaaaaaqm","aaaaaaaaaaaaaaqn","aaaaaaaaaaaaaaqo","aaaaaaaaaaaaaaqp","aaaaaaaaaaaaaaqq","aaaaaaaaaaaaaaqr","aaaaaaaaaaaaaaqs","aaaaaaaaaaaaaaqt","aaaaaaaaaaaaaaqu","aaaaaaaaaaaaaaqv","aaaaaaaaaaaaaaqw","aaaaaaaaaaaaaaqx","aaaaaaaaaaaaaaqy","aaaaaaaaaaaaaaqz","aaaaaaaaaaaaaara","aaaaaaaaaaaaaarb","aaaaaaaaaaaaaarc","aaaaaaaaaaaaaard","aaaaaaaaaaaaaare","aaaaaaaaaaaaaarf","aaaaaaaaaaaaaarg","aaaaaaaaaaaaaarh","aaaaaaaaaaaaaari","aaaaaaaaaaaaaarj","aaaaaaaaaaaaaark","aaaaaaaaaaaaaarl","aaaaaaaaaaaaaarm","aaaaaaaaaaaaaarn","aaaaaaaaaaaaaaro","aaaaaaaaaaaaaarp","aaaaaaaaaaaaaarq","aaaaaaaaaaaaaarr","aaaaaaaaaaaaaars","aaaaaaaaaaaaaart","aaaaaaaaaaaaaaru","aaaaaaaaaaaaaarv","aaaaaaaaaaaaaarw","aaaaaaaaaaaaaarx","aaaaaaaaaaaaaary","aaaaaaaaaaaaaarz","aaaaaaaaaaaaaasa","aaaaaaaaaaaaaasb","aaaaaaaaaaaaaasc","aaaaaaaaaaaaaasd","aaaaaaaaaaaaaase","aaaaaaaaaaaaaasf","aaaaaaaaaaaaaasg","aaaaaaaaaaaaaash","aaaaaaaaaaaaaasi","aaaaaaaaaaaaaasj","aaaaaaaaaaaaaask","aaaaaaaaaaaaaasl","aaaaaaaaaaaaaasm","aaaaaaaaaaaaaasn","aaaaaaaaaaaaaaso","aaaaaaaaaaaaaasp","aaaaaaaaaaaaaasq","aaaaaaaaaaaaaasr","aaaaaaaaaaaaaass","aaaaaaaaaaaaaast","aaaaaaaaaaaaaasu","aaaaaaaaaaaaaasv","aaaaaaaaaaaaaasw","aaaaaaaaaaaaaasx","aaaaaaaaaaaaaasy","aaaaaaaaaaaaaasz","aaaaaaaaaaaaaata","aaaaaaaaaaaaaatb","aaaaaaaaaaaaaatc","aaaaaaaaaaaaaatd","aaaaaaaaaaaaaate","aaaaaaaaaaaaaatf","aaaaaaaaaaaaaatg","aaaaaaaaaaaaaath","aaaaaaaaaaaaaati","aaaaaaaaaaaaaatj","aaaaaaaaaaaaaatk","aaaaaaaaaaaaaatl","aaaaaaaaaaaaaatm","aaaaaaaaaaaaaatn","aaaaaaaaaaaaaato","aaaaaaaaaaaaaatp","aaaaaaaaaaaaaatq","aaaaaaaaaaaaaatr","aaaaaaaaaaaaaats","aaaaaaaaaaaaaatt","aaaaaaaaaaaaaatu","aaaaaaaaaaaaaatv","aaaaaaaaaaaaaatw","aaaaaaaaaaaaaatx","aaaaaaaaaaaaaaty","aaaaaaaaaaaaaatz","aaaaaaaaaaaaaaua","aaaaaaaaaaaaaaub","aaaaaaaaaaaaaauc","aaaaaaaaaaaaaaud","aaaaaaaaaaaaaaue","aaaaaaaaaaaaaauf","aaaaaaaaaaaaaaug","aaaaaaaaaaaaaauh","aaaaaaaaaaaaaaui","aaaaaaaaaaaaaauj","aaaaaaaaaaaaaauk","aaaaaaaaaaaaaaul","aaaaaaaaaaaaaaum","aaaaaaaaaaaaaaun","aaaaaaaaaaaaaauo","aaaaaaaaaaaaaaup","aaaaaaaaaaaaaauq","aaaaaaaaaaaaaaur","aaaaaaaaaaaaaaus","aaaaaaaaaaaaaaut","aaaaaaaaaaaaaauu","aaaaaaaaaaaaaauv","aaaaaaaaaaaaaauw","aaaaaaaaaaaaaaux","aaaaaaaaaaaaaauy","aaaaaaaaaaaaaauz","aaaaaaaaaaaaaava","aaaaaaaaaaaaaavb","aaaaaaaaaaaaaavc","aaaaaaaaaaaaaavd","aaaaaaaaaaaaaave","aaaaaaaaaaaaaavf","aaaaaaaaaaaaaavg","aaaaaaaaaaaaaavh","aaaaaaaaaaaaaavi","aaaaaaaaaaaaaavj","aaaaaaaaaaaaaavk","aaaaaaaaaaaaaavl","aaaaaaaaaaaaaavm","aaaaaaaaaaaaaavn","aaaaaaaaaaaaaavo","aaaaaaaaaaaaaavp","aaaaaaaaaaaaaavq","aaaaaaaaaaaaaavr","aaaaaaaaaaaaaavs","aaaaaaaaaaaaaavt","aaaaaaaaaaaaaavu","aaaaaaaaaaaaaavv","aaaaaaaaaaaaaavw","aaaaaaaaaaaaaavx","aaaaaaaaaaaaaavy","aaaaaaaaaaaaaavz","aaaaaaaaaaaaaawa","aaaaaaaaaaaaaawb","aaaaaaaaaaaaaawc","aaaaaaaaaaaaaawd","aaaaaaaaaaaaaawe","aaaaaaaaaaaaaawf","aaaaaaaaaaaaaawg","aaaaaaaaaaaaaawh","aaaaaaaaaaaaaawi","aaaaaaaaaaaaaawj","aaaaaaaaaaaaaawk","aaaaaaaaaaaaaawl","aaaaaaaaaaaaaawm","aaaaaaaaaaaaaawn","aaaaaaaaaaaaaawo","aaaaaaaaaaaaaawp","aaaaaaaaaaaaaawq","aaaaaaaaaaaaaawr","aaaaaaaaaaaaaaws","aaaaaaaaaaaaaawt","aaaaaaaaaaaaaawu","aaaaaaaaaaaaaawv","aaaaaaaaaaaaaaww","aaaaaaaaaaaaaawx","aaaaaaaaaaaaaawy","aaaaaaaaaaaaaawz","aaaaaaaaaaaaaaxa","aaaaaaaaaaaaaaxb","aaaaaaaaaaaaaaxc","aaaaaaaaaaaaaaxd","aaaaaaaaaaaaaaxe","aaaaaaaaaaaaaaxf","aaaaaaaaaaaaaaxg","aaaaaaaaaaaaaaxh","aaaaaaaaaaaaaaxi","aaaaaaaaaaaaaaxj","aaaaaaaaaaaaaaxk","aaaaaaaaaaaaaaxl","aaaaaaaaaaaaaaxm","aaaaaaaaaaaaaaxn","aaaaaaaaaaaaaaxo","aaaaaaaaaaaaaaxp","aaaaaaaaaaaaaaxq","aaaaaaaaaaaaaaxr","aaaaaaaaaaaaaaxs","aaaaaaaaaaaaaaxt","aaaaaaaaaaaaaaxu","aaaaaaaaaaaaaaxv","aaaaaaaaaaaaaaxw","aaaaaaaaaaaaaaxx","aaaaaaaaaaaaaaxy","aaaaaaaaaaaaaaxz","aaaaaaaaaaaaaaya","aaaaaaaaaaaaaayb","aaaaaaaaaaaaaayc","aaaaaaaaaaaaaayd","aaaaaaaaaaaaaaye","aaaaaaaaaaaaaayf","aaaaaaaaaaaaaayg","aaaaaaaaaaaaaayh","aaaaaaaaaaaaaayi","aaaaaaaaaaaaaayj","aaaaaaaaaaaaaayk","aaaaaaaaaaaaaayl","aaaaaaaaaaaaaaym","aaaaaaaaaaaaaayn","aaaaaaaaaaaaaayo","aaaaaaaaaaaaaayp","aaaaaaaaaaaaaayq","aaaaaaaaaaaaaayr","aaaaaaaaaaaaaays","aaaaaaaaaaaaaayt","aaaaaaaaaaaaaayu","aaaaaaaaaaaaaayv","aaaaaaaaaaaaaayw","aaaaaaaaaaaaaayx","aaaaaaaaaaaaaayy","aaaaaaaaaaaaaayz","aaaaaaaaaaaaaaza","aaaaaaaaaaaaaazb","aaaaaaaaaaaaaazc","aaaaaaaaaaaaaazd","aaaaaaaaaaaaaaze","aaaaaaaaaaaaaazf","aaaaaaaaaaaaaazg","aaaaaaaaaaaaaazh","aaaaaaaaaaaaaazi","aaaaaaaaaaaaaazj","aaaaaaaaaaaaaazk","aaaaaaaaaaaaaazl","aaaaaaaaaaaaaazm","aaaaaaaaaaaaaazn","aaaaaaaaaaaaaazo","aaaaaaaaaaaaaazp","aaaaaaaaaaaaaazq","aaaaaaaaaaaaaazr","aaaaaaaaaaaaaazs","aaaaaaaaaaaaaazt","aaaaaaaaaaaaaazu","aaaaaaaaaaaaaazv","aaaaaaaaaaaaaazw","aaaaaaaaaaaaaazx","aaaaaaaaaaaaaazy","aaaaaaaaaaaaaazz","aaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaab","aaaaaaaaaaaaaaac","aaaaaaaaaaaaaaad","aaaaaaaaaaaaaaae","aaaaaaaaaaaaaaaf","aaaaaaaaaaaaaaag","aaaaaaaaaaaaaaah","aaaaaaaaaaaaaaai","aaaaaaaaaaaaaaaj","aaaaaaaaaaaaaaak","aaaaaaaaaaaaaaal","aaaaaaaaaaaaaaam","aaaaaaaaaaaaaaan","aaaaaaaaaaaaaaao","aaaaaaaaaaaaaaap","aaaaaaaaaaaaaaaq","aaaaaaaaaaaaaaar","aaaaaaaaaaaaaaas","aaaaaaaaaaaaaaat","aaaaaaaaaaaaaaau","aaaaaaaaaaaaaaav","aaaaaaaaaaaaaaaw","aaaaaaaaaaaaaaax","aaaaaaaaaaaaaaay","aaaaaaaaaaaaaaaz","aaaaaaaaaaaaaaba","aaaaaaaaaaaaaabb","aaaaaaaaaaaaaabc","aaaaaaaaaaaaaabd","aaaaaaaaaaaaaabe","aaaaaaaaaaaaaabf","aaaaaaaaaaaaaabg","aaaaaaaaaaaaaabh","aaaaaaaaaaaaaabi","aaaaaaaaaaaaaabj","aaaaaaaaaaaaaabk","aaaaaaaaaaaaaabl","aaaaaaaaaaaaaabm","aaaaaaaaaaaaaabn","aaaaaaaaaaaaaabo","aaaaaaaaaaaaaabp","aaaaaaaaaaaaaabq","aaaaaaaaaaaaaabr","aaaaaaaaaaaaaabs","aaaaaaaaaaaaaabt","aaaaaaaaaaaaaabu","aaaaaaaaaaaaaabv","aaaaaaaaaaaaaabw","aaaaaaaaaaaaaabx","aaaaaaaaaaaaaaby","aaaaaaaaaaaaaabz","aaaaaaaaaaaaaaca","aaaaaaaaaaaaaacb","aaaaaaaaaaaaaacc","aaaaaaaaaaaaaacd","aaaaaaaaaaaaaace","aaaaaaaaaaaaaacf","aaaaaaaaaaaaaacg","aaaaaaaaaaaaaach","aaaaaaaaaaaaaaci","aaaaaaaaaaaaaacj","aaaaaaaaaaaaaack","aaaaaaaaaaaaaacl","aaaaaaaaaaaaaacm","aaaaaaaaaaaaaacn","aaaaaaaaaaaaaaco","aaaaaaaaaaaaaacp","aaaaaaaaaaaaaacq","aaaaaaaaaaaaaacr","aaaaaaaaaaaaaacs","aaaaaaaaaaaaaact","aaaaaaaaaaaaaacu","aaaaaaaaaaaaaacv","aaaaaaaaaaaaaacw","aaaaaaaaaaaaaacx","aaaaaaaaaaaaaacy","aaaaaaaaaaaaaacz","aaaaaaaaaaaaaada","aaaaaaaaaaaaaadb","aaaaaaaaaaaaaadc","aaaaaaaaaaaaaadd","aaaaaaaaaaaaaade","aaaaaaaaaaaaaadf","aaaaaaaaaaaaaadg","aaaaaaaaaaaaaadh","aaaaaaaaaaaaaadi","aaaaaaaaaaaaaadj","aaaaaaaaaaaaaadk","aaaaaaaaaaaaaadl","aaaaaaaaaaaaaadm","aaaaaaaaaaaaaadn","aaaaaaaaaaaaaado","aaaaaaaaaaaaaadp","aaaaaaaaaaaaaadq","aaaaaaaaaaaaaadr","aaaaaaaaaaaaaads","aaaaaaaaaaaaaadt","aaaaaaaaaaaaaadu","aaaaaaaaaaaaaadv","aaaaaaaaaaaaaadw","aaaaaaaaaaaaaadx","aaaaaaaaaaaaaady","aaaaaaaaaaaaaadz","aaaaaaaaaaaaaaea","aaaaaaaaaaaaaaeb","aaaaaaaaaaaaaaec","aaaaaaaaaaaaaaed","aaaaaaaaaaaaaaee","aaaaaaaaaaaaaaef","aaaaaaaaaaaaaaeg","aaaaaaaaaaaaaaeh","aaaaaaaaaaaaaaei","aaaaaaaaaaaaaaej","aaaaaaaaaaaaaaek","aaaaaaaaaaaaaael","aaaaaaaaaaaaaaem","aaaaaaaaaaaaaaen","aaaaaaaaaaaaaaeo","aaaaaaaaaaaaaaep","aaaaaaaaaaaaaaeq","aaaaaaaaaaaaaaer","aaaaaaaaaaaaaaes","aaaaaaaaaaaaaaet","aaaaaaaaaaaaaaeu","aaaaaaaaaaaaaaev","aaaaaaaaaaaaaaew","aaaaaaaaaaaaaaex","aaaaaaaaaaaaaaey","aaaaaaaaaaaaaaez","aaaaaaaaaaaaaafa","aaaaaaaaaaaaaafb","aaaaaaaaaaaaaafc","aaaaaaaaaaaaaafd","aaaaaaaaaaaaaafe","aaaaaaaaaaaaaaff","aaaaaaaaaaaaaafg","aaaaaaaaaaaaaafh","aaaaaaaaaaaaaafi","aaaaaaaaaaaaaafj","aaaaaaaaaaaaaafk","aaaaaaaaaaaaaafl","aaaaaaaaaaaaaafm","aaaaaaaaaaaaaafn","aaaaaaaaaaaaaafo","aaaaaaaaaaaaaafp","aaaaaaaaaaaaaafq","aaaaaaaaaaaaaafr","aaaaaaaaaaaaaafs","aaaaaaaaaaaaaaft","aaaaaaaaaaaaaafu","aaaaaaaaaaaaaafv","aaaaaaaaaaaaaafw","aaaaaaaaaaaaaafx","aaaaaaaaaaaaaafy","aaaaaaaaaaaaaafz","aaaaaaaaaaaaaaga","aaaaaaaaaaaaaagb","aaaaaaaaaaaaaagc","aaaaaaaaaaaaaagd","aaaaaaaaaaaaaage","aaaaaaaaaaaaaagf","aaaaaaaaaaaaaagg","aaaaaaaaaaaaaagh","aaaaaaaaaaaaaagi","aaaaaaaaaaaaaagj","aaaaaaaaaaaaaagk","aaaaaaaaaaaaaagl","aaaaaaaaaaaaaagm","aaaaaaaaaaaaaagn","aaaaaaaaaaaaaago","aaaaaaaaaaaaaagp","aaaaaaaaaaaaaagq","aaaaaaaaaaaaaagr","aaaaaaaaaaaaaags","aaaaaaaaaaaaaagt","aaaaaaaaaaaaaagu","aaaaaaaaaaaaaagv","aaaaaaaaaaaaaagw","aaaaaaaaaaaaaagx","aaaaaaaaaaaaaagy","aaaaaaaaaaaaaagz","aaaaaaaaaaaaaaha","aaaaaaaaaaaaaahb","aaaaaaaaaaaaaahc","aaaaaaaaaaaaaahd","aaaaaaaaaaaaaahe","aaaaaaaaaaaaaahf","aaaaaaaaaaaaaahg","aaaaaaaaaaaaaahh","aaaaaaaaaaaaaahi","aaaaaaaaaaaaaahj","aaaaaaaaaaaaaahk","aaaaaaaaaaaaaahl","aaaaaaaaaaaaaahm","aaaaaaaaaaaaaahn","aaaaaaaaaaaaaaho","aaaaaaaaaaaaaahp","aaaaaaaaaaaaaahq","aaaaaaaaaaaaaahr","aaaaaaaaaaaaaahs","aaaaaaaaaaaaaaht","aaaaaaaaaaaaaahu","aaaaaaaaaaaaaahv","aaaaaaaaaaaaaahw","aaaaaaaaaaaaaahx","aaaaaaaaaaaaaahy","aaaaaaaaaaaaaahz","aaaaaaaaaaaaaaia","aaaaaaaaaaaaaaib","aaaaaaaaaaaaaaic","aaaaaaaaaaaaaaid","aaaaaaaaaaaaaaie","aaaaaaaaaaaaaaif","aaaaaaaaaaaaaaig","aaaaaaaaaaaaaaih","aaaaaaaaaaaaaaii","aaaaaaaaaaaaaaij","aaaaaaaaaaaaaaik","aaaaaaaaaaaaaail","aaaaaaaaaaaaaaim","aaaaaaaaaaaaaain","aaaaaaaaaaaaaaio","aaaaaaaaaaaaaaip","aaaaaaaaaaaaaaiq","aaaaaaaaaaaaaair","aaaaaaaaaaaaaais","aaaaaaaaaaaaaait","aaaaaaaaaaaaaaiu","aaaaaaaaaaaaaaiv","aaaaaaaaaaaaaaiw","aaaaaaaaaaaaaaix","aaaaaaaaaaaaaaiy","aaaaaaaaaaaaaaiz","aaaaaaaaaaaaaaja","aaaaaaaaaaaaaajb","aaaaaaaaaaaaaajc","aaaaaaaaaaaaaajd","aaaaaaaaaaaaaaje","aaaaaaaaaaaaaajf","aaaaaaaaaaaaaajg","aaaaaaaaaaaaaajh","aaaaaaaaaaaaaaji","aaaaaaaaaaaaaajj","aaaaaaaaaaaaaajk","aaaaaaaaaaaaaajl","aaaaaaaaaaaaaajm","aaaaaaaaaaaaaajn","aaaaaaaaaaaaaajo","aaaaaaaaaaaaaajp","aaaaaaaaaaaaaajq","aaaaaaaaaaaaaajr","aaaaaaaaaaaaaajs","aaaaaaaaaaaaaajt","aaaaaaaaaaaaaaju","aaaaaaaaaaaaaajv","aaaaaaaaaaaaaajw","aaaaaaaaaaaaaajx","aaaaaaaaaaaaaajy","aaaaaaaaaaaaaajz","aaaaaaaaaaaaaaka","aaaaaaaaaaaaaakb","aaaaaaaaaaaaaakc","aaaaaaaaaaaaaakd","aaaaaaaaaaaaaake","aaaaaaaaaaaaaakf","aaaaaaaaaaaaaakg","aaaaaaaaaaaaaakh","aaaaaaaaaaaaaaki","aaaaaaaaaaaaaakj","aaaaaaaaaaaaaakk","aaaaaaaaaaaaaakl","aaaaaaaaaaaaaakm","aaaaaaaaaaaaaakn","aaaaaaaaaaaaaako","aaaaaaaaaaaaaakp","aaaaaaaaaaaaaakq","aaaaaaaaaaaaaakr","aaaaaaaaaaaaaaks","aaaaaaaaaaaaaakt","aaaaaaaaaaaaaaku","aaaaaaaaaaaaaakv","aaaaaaaaaaaaaakw","aaaaaaaaaaaaaakx","aaaaaaaaaaaaaaky","aaaaaaaaaaaaaakz","aaaaaaaaaaaaaala","aaaaaaaaaaaaaalb","aaaaaaaaaaaaaalc","aaaaaaaaaaaaaald","aaaaaaaaaaaaaale","aaaaaaaaaaaaaalf","aaaaaaaaaaaaaalg","aaaaaaaaaaaaaalh","aaaaaaaaaaaaaali","aaaaaaaaaaaaaalj","aaaaaaaaaaaaaalk","aaaaaaaaaaaaaall"};

		board = ArrayUtil.strArrayTo2DCharArray(new String[]{"seenew","tmriva","obsibd","wmysen","ltsnsa","iezlgn"});
		words = ArrayUtil.str2strArray(LeetcodeUtils.readText(instance));
		
		
		
		t1 = System.currentTimeMillis();
		results = instance.findWords(board, words);
		t2 = System.currentTimeMillis();
		System.out.println(String.format("results=%s, total time=%,dms", results, (t2 - t1)));
	}
	
	
	static class SolutionV extends WordSearchII {
		public class TrieNode {
	        int count = 0;
	        TrieNode[] children = new TrieNode[26];
	        String s = null;
	        public TrieNode(int count) {
	            this.count = count;
	        }
	    }
	    
	    private TrieNode root;
	    public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        int rows = board.length, cols = board[0].length;
	        Set<String> set = new HashSet<>(1);
	        for (String w : words)
	            set.add(w);
	        // build Trie
	        root = new TrieNode(1);
	        for (String w : set) {
	            TrieNode curr = root;
	            for (char ch : w.toCharArray()) {
	                TrieNode child = curr.children[ch - 'a'];
	                if (child == null) {
	                    child = new TrieNode(1);
	                    curr.children[ch-'a'] = child;
	                }
	                curr = child;
	            }
	            curr.s = w;
	        }
	        List<String> results = new ArrayList<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                Set<Integer> used = new HashSet<>();
	                used.add(r*cols + c); // this modification will never be restored
	                search(board, r, c, root, used, results);
	            }
	        }
	        
	        return results;
	    }
	    
	    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    private void search(char[][] board, int r, int c, TrieNode node, Set<Integer> used, List<String> results) {
	        int rows = board.length, cols = board[0].length;
	        char ch = board[r][c];
	        TrieNode child = node.children[ch-'a'];
	        if (child != null) {
	            if (child.s != null) {
	                results.add(child.s);
	                child.s = null;   // so it won't be added to results again, should it be found at another place on table
	            }
                for (int[] dir : dirs) {
                    int r1 = r + dir[0], c1 = c + dir[1];
                    int p1 = r1*cols + c1;
                    if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && used.add(p1)) {// modify
                        search(board, r1, c1, child, used, results);
                        used.remove(p1);    // restore
                    }
                }
	        }
	    }
	}
	
	
	static class SolutionIV extends WordSearchII {
		public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        Set<String> set = new HashSet<>();
	        for (String w : words)
	            set.add(w);
	        int rows = board.length, cols = board[0].length;
	        List<String> results = new ArrayList<>();
	        for (String w : set) {
	            boolean stop = false;
	            for (int r = 0; r < rows; r++) {
	                for (int c = 0; c < cols; c++) {
	                    if (dfs(board, r, c, w, 0, new HashSet<>())) {
	                        results.add(w);
	                        stop = true;
	                        break;
	                    }
	                }
	                if (stop)
	                    break;
	            }
	        }
	        return results;
	    }
	    
	    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	    private boolean dfs(char[][] board, int r, int c, String w, int idx, Set<Integer> used) {
	        int rows = board.length, cols = board[0].length;
	        if (board[r][c] == w.charAt(idx)) {
	            int p = r*cols + c;
	            used.add(p);    // modify
	            if (idx == w.length()-1)
	                return true;
	            else {
	                for (int[] dir : dirs) {
	                    int r1 = r + dir[0], c1 = c + dir[1];
	                    int p1 = r1*cols + c1;
	                    if (r1 >= 0 && r1 < rows && c1 >= 0 && c1 < cols && !used.contains(p1)
	                        && dfs(board, r1, c1, w, idx+1, used))
	                        return true;
	                }
	            }
	            used.remove(p); // restore
	        }
	        return false;
	    }
	}
	
	
	static class SolutionIII extends WordSearchII {
		private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	    public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        Set<String> set = new HashSet<>();
	        for (String w : words) set.add(w);
	        int rows = board.length, cols = board[0].length;
	        List<String> results = new ArrayList<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                Iterator<String> itr = set.iterator();
	                int[][] used = new int[rows][];
	                int id = 1;
	                while (itr.hasNext()) {
	                    String w = itr.next();
	                    if (bfs(board, r, c, w, used, id++)) {
	                        results.add(w);
	                        itr.remove();
	                    }
	                }
	                if (set.isEmpty())
	                    return results;
	            }
	        }
	        return results;
	    }
	    
	    private boolean bfs(char[][] board, int r, int c, String w, int[][] used, int id) {
	        int rows = board.length, cols = board[0].length;
	        for (int i = 0; i < rows; used[i++] = new int[cols]);
	        List<int[]> ps = new LinkedList<>();
	        ps.add(new int[]{r, c});
//	        used[r][c] = id;
	        int idx = 0;
	        while (!ps.isEmpty()) {
	            List<int[]> nextPs = new LinkedList<>();
	            char ch = w.charAt(idx);
	            for (int[] p : ps) {
	                int r1 = p[0], c1 = p[1];
	                if (board[r1][c1] == ch) {
	                	used[r1][c1] = id;
	                    if (idx == w.length()-1)
	                        return true;
	                    for (int[] dir : dirs) {
	                        int r2 = r1 + dir[0], c2 = c1 + dir[1];
	                        if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols && used[r2][c2] != id) {
//	                            used[r2][c2] = id;
	                            nextPs.add(new int[]{r2, c2});
	                        }
	                    }
	                }
	            }
	            ps = nextPs;
	            idx++;
	        }
	        return false;
	    }
	}
	
	
	// Solution I: TLE
	// use bfs, which requires repeated copy of set to avoid duplicated. very expensive!
	static class SolutionI extends WordSearchII {
		private static final int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	    public List<String> findWords(char[][] board, String[] words) {
	        if (board == null || board.length == 0 || board[0].length == 0)
	            return Collections.emptyList();
	        int rows = board.length, cols = board[0].length;
	        Map<Character, List<Integer>> charIndices = new HashMap<>();
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < cols; c++) {
	                char ch = board[r][c];
	                List<Integer> indices = charIndices.get(ch);
	                if (indices == null) {
	                    indices = new ArrayList<>();
	                    charIndices.put(ch, indices);
	                }
	                indices.add(cols*r + c);
	            }
	        }
	        List<String> results = new ArrayList<>();
	        for (String word : words) {
	            List<Integer> indices = charIndices.get(word.charAt(0));
	            if (indices != null) {
	                for (int index : indices) {
	                    Set<Integer> visited = new HashSet<>();
	                    visited.add(index);
	                    if (dfs(word, board, rows, cols, index, 0, visited)) {
	                        results.add(word);
	                        break;   
	                    }
	                }
	            }
	        }
	        return results;
	    }
	    
	    // dfs instead of bfs; it's actually a dp.
	    private boolean dfs(String word, char[][] board, int rows, int cols, int cell, int idx, Set<Integer> visited) {
	        if (idx == word.length() - 1)
	            return true;
	        char next = word.charAt(idx + 1);
	        int r1 = cell / cols, c1 = cell % cols;
	        for (int[] move : moves) {
	            int r2 = r1 + move[0], c2 = c1 + move[1];
	            if (r2 >= 0 && r2 < rows && c2 >= 0 && c2 < cols) {
	                int cell2 = cols*r2 + c2;
	                if (board[r2][c2] == next && visited.add(cell2)) {    // modify
	                    if (dfs(word, board, rows, cols, cell2, idx+1, visited))
	                        return true;
	                    visited.remove(cell2); // restore
	                }
	            }
	        }
	        return false;
	    }
	}
}
